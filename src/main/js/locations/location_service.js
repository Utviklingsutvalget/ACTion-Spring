angular.module('action').service('LocationService', ['$http', function($http) {
    this.findAll = function() {
        return $http.get('/api/locations');
    };

    this.get = function(id) {
        return $http.get('/api/locations/' + id);
    };

    this.saveLocation = function(location) {
        return $http.post('/api/locations', location);
    };
}]);