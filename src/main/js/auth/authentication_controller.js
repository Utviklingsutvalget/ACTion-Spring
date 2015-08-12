angular.module('action').controller('AuthenticationController', ['$scope', '$routeParams', '$location', 'AuthenticationService', function($scope, $routeParams, $location, AuthenticationService) {
    $scope.$on('oauth:login', function(event, token) {
        console.log('Authorized third party app with token', token.access_token);
        AuthenticationService.exchange(token.access_token).success(function(jwt) {
            console.log("Logged in as:");
            AuthenticationService.login(jwt);
            $scope.loggedIn = AuthenticationService.isLoggedIn();
        });
    });

    $scope.$on('oauth:logout', function() {
        $scope.logout();
    });

    $scope.logout = function() {
        AuthenticationService.logout();
        $location.path('/');
        $scope.loggedIn = AuthenticationService.isLoggedIn();
    };

    $scope.loggedIn = AuthenticationService.isLoggedIn();
}]);