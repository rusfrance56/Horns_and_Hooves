'use strict';
var mainApp = angular.module("mainApp", [
    'ngRoute',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common',
    'personsModule',
    'ordersModule'
]);

mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');

    $routeProvider.when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).when('/persons', {
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    }).otherwise({
        // redirectTO: '/persons'
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    });
}]);