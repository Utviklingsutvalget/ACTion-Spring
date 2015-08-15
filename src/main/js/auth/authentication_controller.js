angular.module('action').controller('AuthenticationController', ['$scope', '$routeParams', '$location', 'AuthenticationService', function($scope, $routeParams, $location, AuthenticationService) {
    $scope.$on('oauth:login', function(event, token) {
        console.log('Authorized third party app with token', token.access_token);
        AuthenticationService.exchange(token.access_token).success(function(jwt) {
            console.log("Logged in as:");
            AuthenticationService.login(jwt);
            $scope.loggedIn = AuthenticationService.isLoggedIn();
        });
    });

    $scope.getBase = function() {
        var port = $location.$$port === 8080 ? ':' + $location.$$port : '';
        return $location.$$protocol + "://" + $location.$$host + port;
    };

    $scope.$on('oauth:logout', function() {
        $scope.logout();
    });

    $scope.logout = function() {
        AuthenticationService.logout();
        $location.path('/');
        $scope.loggedIn = AuthenticationService.isLoggedIn();
    };

    $scope.findRoles = function() {
        AuthenticationService.getMyRoles().then(function(response) {
            $scope.roles = response.data;
        }, function(response) {
            if(response.code === 401) {
                $scope.logout();
            }
        });
    };

    $scope.loggedIn = AuthenticationService.isLoggedIn();
}]);