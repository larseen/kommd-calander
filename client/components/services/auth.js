'use strict';

angular.module('kommdCalendar')
  .factory('Auth', function ($resource) {
    return $resource('/api/auth/');
  });
