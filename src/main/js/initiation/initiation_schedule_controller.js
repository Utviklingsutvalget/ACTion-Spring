angular.module('action').controller('InitiationScheduleController', ['$scope', '$routeParams', 'InitiationService',
    function ($scope, $routeParams, InitiationService) {
        $scope.fetch = function (id) {
            InitiationService.get(id).success(function(schedule) {
                $scope.schedule = schedule;
            });
        };

        $scope.fetchEvents = function() {
            InitiationService.getEventsForSchedule($scope.schedule.id).then(function(response) {
                console.log(response);
                $scope.schedule.events = response.data;
            });
        };

        if(!$scope.schedule) {
            console.log("Fetching schedule!");
            $scope.fetch($routeParams.id);
        }
    }]);
