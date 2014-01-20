'use strict';

angular.module('webApp')
  .controller('MenuCtrl', function ($scope, $rootScope, $location, $log, vertxEventBus) {
    $scope.home = function () {
      if($rootScope.loggedIn) {
        $location.path('/dashboard');
      } else {
        $location.path('/');
      }
    };
    $scope.logout = function () {
      $rootScope.loggedIn = false;
      $rootScope.loggedUser = null;
      $location.path('/');
    };
  });
