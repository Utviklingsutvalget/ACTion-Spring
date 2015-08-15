angular.module('action').controller('UsersController', ['$scope', 'UserService', function($scope, UserService) {
    $scope.findByEmail = function(prefix) {
        delete $scope.error;

        console.log("Searching!");

        UserService.getByEmail(prefix).then(function(success) {
            $scope.user = success.data;
            $scope.$parent.$parent.user = $scope.user;
        }, function(failure) {
            $scope.error = failure.data.message;
            delete $scope.$parent.$parent.user;
        });
    };

    $scope.init = function() {
        console.log("Hi again");
        $scope.postfix = "@student.westerdals.no";
    };

    $scope.init();

}]);
