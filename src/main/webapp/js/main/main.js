'use strict';
var mainApp = angular.module("mainApp", [
    'ngRoute',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common',
    'employeeModule',
    'ordersModule'
]);

mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');

    $routeProvider.when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).when('/employee', {
        templateUrl: 'views/employee/viewEmployee.html',
        controller: 'EmpsController'
    }).otherwise({
        // redirectTO: '/employee'
        templateUrl: 'views/employee/viewEmployee.html',
        controller: 'EmpsController'
    });
}]);