angular.module('action').service('AuthenticationService', ['$http', '$localStorage', function($http, $localStorage) {
    if($localStorage.jwt) {
        console.log('Setting default header for x-auth');
        $http.defaults.headers.common['x-auth'] = $localStorage.jwt.token;
    } else {
        console.log('Not setting default header for x-auth');
    }
    this.exchange = function(token) {
        return $http.post('/api/authenticate/exchange', token);
    };

    this.login = function(jwt) {
        $localStorage.jwt = jwt;
        $http.defaults.headers.common['x-auth'] = jwt.token;
    };

    this.logout = function() {
        delete $http.defaults.headers.common['x-auth'];
        delete $localStorage.jwt;
    };
}]);