angular.module('action').config(['$routeProvider', function ($routeProvider) {
    console.log("Hello");
    $routeProvider
        .when('/locations', {
            templateUrl: '/views/locations/index.html',
            controller: 'LocationsController'
        })
        .when('/authenticate', {
            templateUrl: '/views/authentication/index.html'
        })
        .when('/initiation', {
            templateUrl: '/views/initiation/index.html',
            controller: 'InitiationSchedulesController  '
        })
        .when('/initiation/new', {
            templateUrl: '/views/initiation/form.html',
            controller: 'InitiationSchedulesController'
        })
        .when('initiation/:id', {
            templateUrl: 'views/initiation/show.html',
            controller: 'InitiationScheduleController'
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
