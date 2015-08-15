angular.module("action").controller("FeedController", ["$scope", "$routeParams", "FeedsService", function($scope, $routeParams, FeedsService){

    $scope.editActive = "active";
    $scope.previewActive = "";

    var fetchFeedInfo = function(){
        if($routeParams.id === undefined){
            $scope.search = {};
            return;
        }

        FeedsService.fetchById($routeParams.id).then(function(object){
            $scope.feed = object.data;
            console.log(object);
        }, function(error){
            $scope.errorMessage = error;
            $scope.search = {};
        });
    };

    var addImageToFeed = function(){
        $scope.feed.image = $scope.image;
    };

    $scope.submit = function(){
        addImageToFeed();
        FeedsService.save($scope.feed).then(function(message){
            $scope.feedBack = message;
        }, function(error){
            $scope.errorMessage = error;
        });
    };

    $scope.previewMd = function(){
        $scope.preview = !$scope.preview;
        console.log("Swapped preview, preview is now: " + $scope.preview);
    };

    fetchFeedInfo();
}]);