angular.module('action').controller('InitiationScheduleController', ['$scope', '$routeParams', 'InitiationSchedulesService',
    function ($scope, $routeParams, InitiationSchedulesService) {
        $scope.fetch = function (id) {
            InitiationSchedulesService.get(id).success(function(schedule) {
                $scope.schedule = schedule;
            });
        };

        if(!$scope.schedule) {
            $scope.fetch($routeParams.id);
        }
    }]);
