angular.module('action').config(['$routeProvider', function ($routeProvider) {
    console.log("Hello");
    $routeProvider
        .when('/locations', {
            templateUrl: '/views/locations/index.html',
            controller: 'LocationsController'
        })
        .when('/locations/new', {
            templateUrl: '/views/locations/form.html',
            controller: 'LocationsController'
        })
        .when('/locations/:id', {
            templateUrl: '/views/locations/show.html',
            controller: 'LocationController'
        })
        .otherwise({
            templateUrl: '/views/notFound.html'
        });
}]);