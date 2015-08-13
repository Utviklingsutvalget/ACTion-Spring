angular.module('action').controller('InitiationEventController', [
    '$scope', '$routeParams', '$location', 'InitiationService',
    function ($scope, $routeParams, $location, InitiationService) {

        $scope.onTimeSet = function (newDate) {
            console.log(newDate);
        };

        $scope.findEvent = function (id) {
            return InitiationService.getEvent(id).then(function(reponse) {
                $scope.event = reponse.data;
            });
        };

        $scope.beforeRender = function ($view, $dates, $leftDate, $upDate, $rightDate) {

        };

        $scope.createEvent = function () {
            console.log($scope.event);
            InitiationService.saveEvent($scope.event).then(function (response) {
                console.log("Saved:");
                console.log(response);
                $location.path('/initiation/events/' + response.data.id);
            });
        };

        if ($routeParams.id) {
            $scope.findEvent($routeParams.id);
        }

    }]);