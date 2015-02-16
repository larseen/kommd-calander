angular.module('kommdCalendar')
.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/main/main.html',
        controller: 'MainCtrl'
        // authenticate: true
    });
});