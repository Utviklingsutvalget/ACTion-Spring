angular.module('action').controller('LocationsController', ['$scope', '$location', 'LocationService', function ($scope, $location, LocationService) {
    $scope.fetchAll = function() {
        console.log("Fetching all locations");
        LocationService.findAll().success(function (locations) {
            console.log(locations);
            $scope.locations = locations;
        });
    };

    $scope.createLocation = function() {
        LocationService.saveLocation($scope.location).success(function(location) {
            $scope.location = location;
            $location.path('/locations/' + location.id);
        });
    };

    if(!$scope.locations) {
        $scope.fetchAll();
    }
}]);
