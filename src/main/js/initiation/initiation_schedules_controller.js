angular.module('action').controller('InitiationSchedulesController', ['$scope', '$routeParams', '$location',
    'InitiationSchedulesService',
    function ($scope, $routeParams, $location, InitiationSchedulesService) {

        $scope.fetchAll = function() {
            InitiationSchedulesService.findAll().success(function(InitiationSchedules) {
                $scope.schedules = InitiationSchedules;
            });
        };

        $scope.createSchedule = function() {
            InitiationSchedulesService.saveSchedule($scope.schedule).success(function(schedule) {
                $scope.schedule = schedule;
                $location.path('/initiation/' + schedule.id);
            });
        };

        if (!$scope.schedules) {
            $scope.fetchAll();
        }
    }]);
