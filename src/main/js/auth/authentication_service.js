angular.module('action').service('AuthenticationService', ['$http', '$localStorage', function($http, $localStorage) {
    if($localStorage.jwt) {
        console.log('Setting default header for x-auth');
        $http.defaults.headers.common['x-auth'] = $localStorage.jwt.token;
    } else {
        console.log('Not setting default header for x-auth');
    }
    this.exchange = function(token) {
        return $http.post('/authenticate/exchange', token);
    };

    this.isLoggedIn = function() {
        return $localStorage.jwt !== undefined;
    };

    this.login = function(jwt) {
        $localStorage.jwt = jwt;
        $http.defaults.headers.common['x-auth'] = jwt.token;
    };

    this.getMyRoles = function() {
        return $http.get('/api/users/me/roles');
    };

    this.logout = function() {
        delete $http.defaults.headers.common['x-auth'];
        delete $localStorage.jwt;
        delete $localStorage.token;
    };
}]);