angular.module("action").controller("FeedController", ["$scope", "$routeParams", "FeedsService", function($scope, $routeParams, FeedsService){

    $scope.editActive = "active";
    $scope.previewActive = "";

    var fetchFeedInfo = function(){
        FeedsService.fetchById($routeParams.id).then(function(feed){
            $scope.feed = feed;
        }, function(error){
            $scope.error = error;
            $scope.search = {};
        });
    };

    $scope.submit = function(){
        FeedsService.save($scope.feed).then(function(message){
            $scope.feedBack = message;
        }, function(error){
            $scope.feedBack = error;
        });
    };

    $scope.previewMd = function(){
        $scope.preview = !$scope.preview;
        console.log("Swapped preview, preview is now: " + $scope.preview);
    };
}]);