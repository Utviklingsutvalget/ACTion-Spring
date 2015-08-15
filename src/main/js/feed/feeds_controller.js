angular.module("action").controller("FeedsController", ["$scope", "FeedsService", function($scope, FeedsService){

    var fetchAllFeeds = function(){
        FeedsService.all().then(function(object){
            $scope.feeds = object.data;
        });
    };

    fetchAllFeeds();
}]);