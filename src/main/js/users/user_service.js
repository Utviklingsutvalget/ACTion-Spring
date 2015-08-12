angular.module('action').service('UserService', ['$http', function($http) {
    this.getMe = function() {
        return $http.get('/api/users/me');
    }
}]);