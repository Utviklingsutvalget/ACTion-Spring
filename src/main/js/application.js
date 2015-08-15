var app = angular.module('action', [
    'ngRoute',
    'ui.bootstrap.datetimepicker',
    'btford.markdown',
    'ngFileUpload',
    'oauth'
]);

app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');
}]);

app.run(['$rootScope', function($rootScope) {
    $rootScope.refreshFoundation = function(functionToRefresh) {
        console.log('refreshing foundation');
        $(document).foundation(functionToRefresh, 'reflow');
    };

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        var route = current.$$route;
        if(route) {
            var title = route.title;
            $rootScope.title = title ? title : 'SWACT';
        } else {
            $rootScope.title = "404";
        }
    });
}]);
