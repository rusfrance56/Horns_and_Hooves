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
    $locationProvider.hashPrefix('');
    $routeProvider.when('/orders/editOrder/:id', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'EditOrderController',
        resolve: {
            order: function (OrdersService, $route) {
                return OrdersService.getOrderById($route.current.params.id);
            }
        }
    }).when('/orders/editOrder/', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'EditOrderController',
        resolve: {
            order: function () {
                return {};
            }
        }
    }).when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).otherwise({
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    });
}]);
