angular.module('action').controller('AuthenticationController', ['$scope', '$routeParams', 'AuthenticationService', function($scope, $routeParams, AuthenticationService) {
    $scope.$on('oauth:login', function(event, token) {
        console.log('Authorized third party app with token', token.access_token);
        AuthenticationService.exchange(token.access_token).success(function(jwt) {
            console.log("Logged in as:");
            AuthenticationService.login(jwt);
        });
    });

    $scope.$on('oauth:logout', function() {
        AuthenticationService.logout();
    });
}]);