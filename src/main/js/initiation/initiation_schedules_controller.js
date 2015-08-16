angular.module('action').controller('InitiationSchedulesController', ['$scope', '$routeParams', '$location',
    'InitiationService',
    function ($scope, $routeParams, $location, InitiationService) {

        $scope.fetchAll = function() {
            InitiationService.findAll().success(function(schedules) {
                $scope.schedules = schedules;
            });
        };

        $scope.createSchedule = function() {
            InitiationService.saveSchedule($scope.schedule).success(function(schedule) {
                $scope.schedule = schedule;
                $location.path('/initiation/' + schedule.id);
            });
        };

        if (!$scope.schedules) {
            $scope.fetchAll();
        }
    }]);
