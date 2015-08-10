var app = angular.module('action', [
    'ngRoute'
]);

app.config(['$locationProvider', function($locationProvider) {
    $locationProvider.html5Mode(false).hashPrefix('!');
}]);
