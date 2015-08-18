angular.module('action').controller('InitiationSchedulesController', ['$scope', '$routeParams', '$location',
    'InitiationService', 'amMoment',
    function ($scope, $routeParams, $location, InitiationService, amMoment) {

        $scope.fetchAll = function() {
            InitiationService.findAll().success(function(schedules) {
                $scope.schedules = schedules;
            });
        };

        $scope.select = function(schedule) {
            $scope.selected = schedule;
        };

        $scope.formatToDateOnly = function(event) {
            var stamp = event.dateTime;
            stamp = amMoment.preprocessDate(stamp);
            var date = moment(stamp);
            if (!date.isValid()) {
                return '';
            }

            var timeZoned = amMoment.applyTimezone(date);
            return timeZoned.startOf('day');
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
