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
            id: 1,
            title: "Arne",
            text: "#Snippet here " +
            "##Hei",
            snippet: "Omg what a lovely snippetdddddd Omg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetddddddOmg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetddddddOmg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetdddddd" +
            "Omg what a lovely snippetddddddOmg what a lovely snippetddddddOmg what a lovely snippetdddddd",
            image: {
                url: "https://i.imgur.com/csgtUSw.jpg"
            }
        }
    ];
}]);