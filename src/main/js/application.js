var app = angular.module('action', [
    'ngRoute',
    'oauth'
]);

app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(true).hashPrefix('!');
}]);
