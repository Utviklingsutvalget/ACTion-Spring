angular.module('action').controller('InitiationScheduleController', ['$scope', '$routeParams', 'InitiationService',
    function ($scope, $routeParams, InitiationService) {
        $scope.fetch = function (id) {
            InitiationService.get(id).success(function(schedule) {
                $scope.schedule = schedule;
            });
        };

        if(!$scope.schedule) {
            $scope.fetch($routeParams.id);
        }
    }]);
