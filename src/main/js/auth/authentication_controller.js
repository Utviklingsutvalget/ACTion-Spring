angular.module('action').controller('AuthenticationController', ['$scope', '$routeParams', '$location', 'AuthenticationService', function($scope, $routeParams, $location, AuthenticationService) {
    $scope.$on('oauth:login', function(event, token) {
        console.log('Authorized third party app with token', token.access_token);
        AuthenticationService.exchange(token.access_token).success(function(jwt) {
            console.log("Logged in as:");
            AuthenticationService.login(jwt);
            $scope.loggedIn = AuthenticationService.isLoggedIn();
            $location.path('/me');
        });
    });

    $scope.getBase = function() {
        var port = $location.$$port === 8080 ? ':' + $location.$$port : '';
        return $location.$$protocol + "://" + $location.$$host + port;
    };

    $scope.$on('oauth:logout', function() {
        $scope.logout();
    });

    $scope.getClientId = function() {
        var base = $scope.getBase();
        var s = 'swact.no';
        if(base.indexOf(s, base.length - s.length) !== -1) {
            return '8688425750-unop2on34k38r414k03rsll55ep5prq2.apps.googleusercontent.com';
        } else {
            return '13727726289-v7nev9lo4apgj55eqbde7n1h4ghu5a9t.apps.googleusercontent.com';
        }
    };

    $scope.logout = function() {
        AuthenticationService.logout();
        $location.path('/authenticate');
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