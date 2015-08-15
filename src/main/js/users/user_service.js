angular.module('action').service('UserService', ['$http', function ($http) {
    this.getMe = function () {
        return $http.get('/api/users/me');
    };

    this.getByEmail = function (prefix) {
        return $http.get('/api/users/search/' + prefix);
    };

    this.removeRole = function (user, role) {
        return $http.delete('/api/users/' + user + '/roles/' + role);
    };

    this.addRole = function (user, role) {
        return $http.post('/api/users/' + user + '/roles', {
            name: role
        });
    };

    this.findUsersByRole = function (role) {
        return $http.get('/api/roles/' + role + '/users');
    };

}]);