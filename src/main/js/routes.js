angular.module('action').config(['$routeProvider', function ($routeProvider) {
    console.log("Hello");
    $routeProvider
    	.when('/', {
            templateUrl: '/views/feed/index.html',
            controller: 'FeedsController'
    	})
        .when('/authenticate', {
            title: 'Logg inn',
            templateUrl: '/views/authentication/index.html'
        })
        .when('/initiation', {
            templateUrl: '/views/initiation/index.html',
            controller: 'InitiationSchedulesController'
        })
        .when('/initiation/new', {
            templateUrl: '/views/initiation/form.html',
            controller: 'InitiationSchedulesController'
        })
        .when('/initiation/users', {
            templateUrl: '/views/initiation/users/index.html',
            controller: 'InitiationAdminsController'
        })
        .when('/initiation/:id', {
            templateUrl: '/views/initiation/show.html',
            controller: 'InitiationScheduleController'
        })
        .when('/admin/locations', {
            title: 'Lokasjoner',
            templateUrl: '/views/locations/admin/index.html',
            controller: 'LocationsController'
        })
        .when('/admin/locations/new', {
            title: 'Lokasjoner',
            templateUrl: '/views/locations/admin/form.html',
            controller: 'LocationsController'
        })
        .when('/admin/locations/:id', {
            title: 'Lokasjoner',
            templateUrl: '/views/locations/admin/show.html',
            controller: 'LocationController'
        })
        .when('/me', {
            title: 'Profil',
            templateUrl: '/views/users/index.html',
            controller: 'UserController'
        })
        .when('/admin', {
            templateUrl: '/views/admin/index.html',
            controller: 'AdminController'
        })
        .when('/admin/initiation', {
            templateUrl: '/views/initiation/admin/index.html'
        })
        .when('/initiation/events/new', {
            templateUrl: '/views/initiation/events/admin/form.html',
            controller: 'InitiationEventController'
        })
        .when('/initiation/events/:id', {
            templateUrl: '/views/initiation/events/show.html',
            controller: 'InitiationEventController'
        })
        .when('/initiation/events/:id/edit', {
            templateUrl: '/views/initiation/events/admin/form.html',
            controller: 'InitiationEventController'
        })
                .when('/feed', {
            templateUrl: '/views/feed/index.html',
            controller: 'FeedsController'
        })
        .when('/feed/new', {
            templateUrl: '/views/feed/form.html',
            controller: 'FeedController'
        })
        .when('/feed/:id', {
            templateUrl: '/views/feed/show.html',
            controller: 'FeedController'
        })
        .when('/feed/:id/edit', {
            templateUrl: '/views/feed/form.html',
            controller: 'FeedController'
        })
        .otherwise({
            title: '404',
            templateUrl: '/views/notFound.html'
        });
}]);
