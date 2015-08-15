angular.module('action').controller('UserController', ['$scope', 'UserService', function($scope, UserService) {

    $scope.fetchMe = function() {
        UserService.getMe().success(function(user) {
            $scope.user = user;
            });
    };
    if(!$scope.user) {
        $scope.fetchMe();
    }
}]);