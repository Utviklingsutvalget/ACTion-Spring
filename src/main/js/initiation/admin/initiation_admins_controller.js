angular.module('action').controller('InitiationAdminsController',
    ['$scope', 'UserService', function ($scope, UserService) {

        $scope.findUsers = function() {
            UserService.findUsersByRole('INITIATION').then(function(success) {
                $scope.users = success.data;
            });
        };

        $scope.removeFromCommittee = function(user) {
            console.log("adding user " + user);
            UserService.removeRole(user.id, 'INITIATION').then(function(success) {
                var index = $scope.users.indexOf(user);
                $scope.users.splice(index);
            });
        };

        $scope.addToCommittee = function(user) {
            console.log("removing user " + user);
            UserService.addRole(user.id, 'INITIATION').then(function(success) {
                $scope.users.push(user);
            });
        };

        $scope.findUsers();
    }]);