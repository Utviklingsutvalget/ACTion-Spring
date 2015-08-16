angular.module('action').controller('InitiationEventController', [
    '$scope', '$routeParams', '$location', 'InitiationService',
    function ($scope, $routeParams, $location, InitiationService) {

        $scope.onTimeSet = function (newDate) {
            console.log(newDate);
        };

        $scope.findEvent = function (id) {
            return InitiationService.getEvent(id).then(function(response) {
                console.log(response.data);
                $scope.event = response.data;
                $scope.image = $scope.event.image;
                $scope.setImage = $scope.event.image;
            });
        };

        $scope.createEvent = function () {
            $scope.event.image = $scope.image;
            console.log($scope.event);
            InitiationService.saveEvent($scope.event).then(function (response) {
                console.log("Saved:");
                console.log(response);
                $location.path('/initiation/events/' + response.data.id);
            });
        };

        $scope.addScheduleToEvent = function(schedule) {
            if(!schedule) {
                return;
            }
            if(!$scope.event) {
                $scope.event = {};
            }
            if(!$scope.event.schedules) {
                $scope.event.schedules = [];
            }
            if($scope.event.schedules.indexOf(schedule) === -1) {
                $scope.event.schedules.push(schedule);
            }
        };

        $scope.removeScheduleFromEvent = function(schedule) {
            var schedules = $scope.event.schedules;
            var index = schedules.indexOf(schedule);
            schedules.splice(index, 1);
        };

        if ($routeParams.id) {
            $scope.findEvent($routeParams.id);
        }

    }]);