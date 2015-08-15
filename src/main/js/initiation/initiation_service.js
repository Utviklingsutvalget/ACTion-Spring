angular.module('action').service('InitiationService', ['$http', function ($http) {
    this.findAll = function () {
        return $http.get('/api/initiation');
    };

    this.get = function (id) {
        return $http.get('/api/initiation/' + id);
    };

    this.saveSchedule = function (schedule) {
        return $http.post('/api/initiation', schedule);
    };

    this.getEventsForSchedule = function (id) {
        return $http.get('/api/initiation/' + id + '/events')
    };

    this.getAllEvents = function () {
        return $http.get('/api/initiation/events');
    };

    this.saveEvent = function (event) {
        return $http.post('/api/initiation/events', event);
    };

    this.getEvent = function (id) {
        return $http.get('/api/initiation/events/' + id);
    };
}]);

