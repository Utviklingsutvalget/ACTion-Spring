angular.module('action').controller('InitiationSchedulesController', ['$scope', '$routeParams', '$location',
    'InitiationService',
    function ($scope, $routeParams, $location, InitiationService) {

        $scope.fetchAll = function() {
            InitiationService.findAll().success(function(InitiationSchedules) {
                $scope.schedules = InitiationSchedules;
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
