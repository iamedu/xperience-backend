'use strict';

angular.module('webApp', [
  'ngCookies',
  'ngResource',
  'ngSanitize',
  'ngRoute',
  'knalli.angular-vertxbus'
])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'DashboardCtrl'
      })
      .when('/controller', {
        templateUrl: 'views/controller.html',
        controller: 'ControllerCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .run(function ($location, $rootScope) {
    if($location.path() !== '/' && !$rootScope.loggedIn) {
      $location.path('/');
    }
  });
