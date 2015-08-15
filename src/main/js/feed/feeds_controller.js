angular.module("action").controller("FeedsController", ["$scope", "FeedsService", function($scope, FeedsService){

    $scope.feeds = function(){
        FeedsService.all().when(function(feeds){
            return feeds;
        }, function(){
            return;
        });
    };
}]);