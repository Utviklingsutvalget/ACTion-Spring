angular.module('action').service('InitiationSchedulesService', ['$http', function($http) {
    this.findAll = function() {
        return $http.get('/api/initiation');
    };

    this.get = function(id) {
        return $http.get('/api/initiation/' + id);
    };

    this.saveSchedule = function(schedule) {
        return $http.post('/api/initiation', schedule);
    }
}]);

