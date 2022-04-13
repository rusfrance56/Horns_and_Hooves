'use strict';
var ordersModule = angular.module("ordersModule", [
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
ordersModule.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $stateProvider
        .state('orders', {
            url: '/orders',
            templateUrl: 'views/orders/viewOrders.html',
            controller: 'OrdersController'
        })
        .state('orders_create', {
            url: '/orders/createOrder',
            templateUrl: 'views/orders/editOrder.html',
            controller: 'EditOrderController',
            resolve: {
                order: function () {
                    return {};
                }
            }
        })
        .state('orders_edit', {
            url: '/orders/editOrder/:id',
            templateUrl: 'views/orders/editOrder.html',
            controller: 'EditOrderController',
            resolve: {
                order: function (OrdersService, CommonService, $stateParams, $state) {
                    return OrdersService.getOrderById($stateParams.id).then(function (response) {
                        return response.order;
                    }, function (response) {
                        CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                        $state.go('orders_create');
                    });
                }
            }
        });
    $urlRouterProvider.otherwise('/orders');
}]);

