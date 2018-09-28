'use strict';
var ordersModule = angular.module("ordersModule", [
        'ngRoute',
        'ui.router',
        'smart-table',
        'pascalprecht.translate',
        'ui.bootstrap',
        'Common'
    ]);
ordersModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    /*$locationProvider.hashPrefix('');
    $routeProvider.when('/orders/editOrder/:id', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'EditOrderController'
    }).when('/orders/editOrder/', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'EditOrderController'
    }).when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).otherwise({
        // redirectTO: '/employee'
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    });*/
}]);
