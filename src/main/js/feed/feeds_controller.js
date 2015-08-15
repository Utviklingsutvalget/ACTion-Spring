angular.module("action").controller("FeedsController", ["$scope", "FeedsService", function($scope, FeedsService){

    console.log("Hello world");

    $scope.feeds = function(){
        FeedsService.all().when(function(feeds){
            return feeds;
        }, function(){
            return;
        });
    };

    $scope.feeds = [
        {
            title: "Arne",
            text: "#Snippet here " +
            "##Hei",
            snippet: "Omg what a lovely snippet",
            image: {
                url: "https://i.imgur.com/csgtUSw.jpg"
            }
        }
    ];
}]);