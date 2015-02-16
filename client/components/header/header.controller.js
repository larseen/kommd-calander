angular.module('kommdCalendar')
.controller('HeaderCtrl', function ($scope, $location, Auth){


    $scope.routes = [{
        name : 'Groups',
        link : '/groups',
        glyph: 'glyphicon glyphicon-user'
    }];

    var activePath = $location.path();
    for(var i = 0; i < $scope.routes.length; i++) {
        if($scope.routes[i].link == activePath) $scope.routes[i].class = 'active';
    }

     $scope.logout = function() {
        Auth.logout().then(function() {
            $location.path('/login');
        });
    };

});