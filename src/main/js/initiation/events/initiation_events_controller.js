angular.module('action').controller('InitiationEventsController',
    ['$scope', 'InitiationService', function ($scope, InitiationService) {
        $scope.findAll = function () {
            InitiationService.getAllEvents().then(function (response) {
                $scope.events = response.data;
            });
        };

        if(!$scope.events) {
            $scope.findAll();
        }
    }]);
