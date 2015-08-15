angular.module("action").controller("FeedsController", ["$scope", "FeedsService", function($scope, FeedsService){

    console.log("Hello world");

    $scope.feeds = function(){
        FeedsService.all().when(function(feeds){
            return feeds;
        }, function(){
            return;
        });
    };
}]);