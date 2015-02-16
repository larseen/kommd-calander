angular.module('kommdCalendar', [
    'ngResource',
    'ngCookies',
    'ui.bootstrap',
    'ngLocale',
    'ngRoute'
])
.config(function ($routeProvider, $locationProvider, $httpProvider) {
    $routeProvider
        .otherwise({
            redirectTo:'/'
             //authenticate: true
        });
      $locationProvider.html5Mode(true);
  });

