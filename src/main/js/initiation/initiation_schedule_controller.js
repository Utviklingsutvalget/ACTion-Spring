angular.module('action').controller('InitiationScheduleController', ['$scope', '$routeParams', 'InitiationService',
    'amMoment',
    function ($scope, $routeParams, InitiationService, amMoment) {
        $scope.fetch = function (id) {
            InitiationService.get(id).success(function(schedule) {
                $scope.schedule = schedule;
            });
        };

        $scope.formatToDateOnly = function(stamp) {
            stamp = amMoment.preprocessDate(stamp);
            var date = moment(stamp);
            if (!date.isValid()) {
                return '';
            }

            return amMoment.applyTimezone(date).format('Do MMMM');
        };

        $scope.fetchEvents = function() {
            InitiationService.getEventsForSchedule($scope.schedule.id).then(function(response) {
                console.log(response);
                $scope.schedule.events = response.data;

                $scope.$root.refreshFoundation('tab');
            });
        };

        if(!$scope.schedule) {
            console.log("Fetching schedule!");
            $scope.fetch($routeParams.id);
        }
    }]);
