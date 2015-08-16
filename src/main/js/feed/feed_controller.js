angular.module("action").controller("FeedController", ["$scope", "$routeParams", "FeedsService", function($scope, $routeParams, FeedsService){

    $scope.editActive = "active";
    $scope.previewActive = "";

    var fetchFeedInfo = function(){
        if($routeParams.id === undefined){
            $scope.feed = {};
            return;
        }

        FeedsService.fetchById($routeParams.id).then(function(object){
            $scope.feed = object.data;
            $scope.currentImage = $scope.feed.image;
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
        delete $scope.feedbackMessage;
        delete $scope.errorMessage;

        addImageToFeed();

        FeedsService.save($scope.feed).then(function(message){
            $scope.feedbackMessage = "Lagret!";
        }, function(error){
            $scope.errorMessage = error;
        });
    };

    $scope.previewMd = function(){
        $scope.preview = !$scope.preview;
    };

    fetchFeedInfo();
}]);