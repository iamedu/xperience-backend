'use strict';

angular.module('webApp')
  .controller('MenuCtrl', function ($scope, $rootScope, $location, $log, vertxEventBus) {
    $scope.logout = function () {
      $rootScope.loggedIn = false;
      $location.path('/');
    };
  });
