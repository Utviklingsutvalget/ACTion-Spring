angular.module("action").service("FeedsService", ["$http", function($http){

    this.all = function(){
        return $http.get("/api/feed");
    };
}]);