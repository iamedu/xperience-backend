'use strict';

angular.module('webApp')
  .controller('MainCtrl', function ($scope, $rootScope, $location, $log, vertxEventBus) {
    $scope.login = function (username, password) {
      vertxEventBus.login(username, password, function (reply) {
        if(reply && reply.status === 'ok') {
          $rootScope.loggedIn = true;
          $location.path('/dashboard');
        } else {
          $rootScope.loggedIn = false;
        }
        $scope.$apply();
      });
    };
  });
