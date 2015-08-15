angular.module("action").controller("FeedController", ["$scope", "$routeParams", "FeedsService", function($scope, $routeParams, FeedsService){

    var fetchFeedInfo = function(){
        FeedsService.fetchById($routeParams.id).then(function(feed){
            $scope.feed = feed;
        }, function(error){
            $scope.error = error;
            $scope.search = {};
        });
    };

    $scope.save = function(search){
        FeedsService.save(search).when(function(message){
            $scope.feedBack = message;
        }, function(error){
            $scope.feedBack = error;
        });
    };
}]);