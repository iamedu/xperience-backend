'use strict';

angular.module('webApp')
  .controller('DashboardCtrl', function ($scope, $rootScope, $log, vertxEventBus) {
    var listSearch = function () {
      vertxEventBus.send('xperience.search.list', {
        username: $rootScope.loggedUser
      },
      function (reply) {
        $scope.noSearch = (reply.number === 0);
        $scope.showCreateSearch = (reply.number === 0);
        if(reply.number > 0) {
          $scope.search = reply.results[0];
        }
        $scope.$apply();
      });
    };

    $scope.saveSearch = function (search) {
      $log.log(search);
      vertxEventBus.send('xperience.search.save', {
        username: $rootScope.loggedUser,
        search: search
      },
      function (reply) {
        $scope.showCreateSearch = false;
        $scope.$apply();
      });
      listSearch();
    };

    listSearch();
  });
