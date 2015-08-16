angular.module("action").service("FeedsService", ["$http", function($http){

    this.all = function(){
        return $http.get("/api/feed");
    };

    this.fetchById = function(id){
        return $http.get("/api/feed/" + id);
    };

    this.save = function(feed){
        return $http.post("/api/feed", feed);
    };
}]);