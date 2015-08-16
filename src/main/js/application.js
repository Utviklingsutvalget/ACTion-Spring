moment.tz.add({
    "zones": {
        "Europe/Amsterdam": [
            "0:19:32 - LMT 1835 0:19:32",
            "0:19:32 Neth %s 1937_6_1 1:19:32",
            "0:20 Neth NE%sT 1940_4_16_0 0:20",
            "1 C-Eur CE%sT 1945_3_2_2 1",
            "1 Neth CE%sT 1977 1",
            "1 EU CE%sT"
        ]
    },
    "links": {
    }
});

var app = angular.module('action', [
    'ngRoute',
    'ui.bootstrap.datetimepicker',
    'btford.markdown',
    'angular.filter',
    'angularMoment',
    'ngFileUpload',
    'oauth'
]);

app.constant('angularMomentConfig', {
    preprocess: 'unix', // optional
    timezone: 'Europe/Amsterdam' // optional
});

app.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: true,
        rewriteLinks: true
    }).hashPrefix('!');
}]);

app.run(['$rootScope', 'amMoment', function ($rootScope, amMoment) {
    amMoment.changeLocale('nb');

    $rootScope.refreshFoundation = function (functionToRefresh) {
        console.log('refreshing foundation');
        $(document).foundation(functionToRefresh, 'reflow');
    };

    $rootScope.$on('$routeChangeSuccess', function (event, current, previous) {
        var route = current.$$route;
        if (route) {
            var title = route.title;
            $rootScope.title = title ? title : 'SWACT';
        } else {
            $rootScope.title = "404";
        }
    });
}]);
