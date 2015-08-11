angular.module('action').service('LocationService', ['$http', function($http) {
    this.findAll = function() {
        console.log("Getting all locations");
        return $http.get('/api/locations');
    };

    this.get = function(id) {
        console.log("Getting location with id " + id);
        return $http.get('/api/locations/' + id);
    };

    this.saveLocation = function(location) {
        return $http.post('/api/locations', location);
    };
}]);