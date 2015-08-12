angular.module('action').controller('LocationController', ['$scope', '$routeParams', 'LocationService',
    function ($scope, $routeParams, LocationService) {
        $scope.fetch = function (id) {
            LocationService.get(id).success(function(location) {
                $scope.location = location;
            });
        };

        if(!$scope.location) {
            $scope.fetch($routeParams.id);
        }
    }]);
