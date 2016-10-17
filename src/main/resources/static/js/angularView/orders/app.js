'use strict';

angular.module('ngdemo', ['ngdemo.filters','ngdemo.services',
                'ngdemo.directives', 'ngdemo.controllers'])
    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/orders', {
                templateUrl: 'viewOrders.htm',
                controller: 'OrdersCtrl'
            })
            .when('/addOrder', {
                templateUrl: 'addOrder.htm',
                controller: 'OrderCreationCtrl'
            })
            .when('/editOrder/:id', {
                templateUrl: 'editOrder.htm',
                controller: 'OrderDetailCtrl'
            })
            .otherwise({
                redirectTo: '/orders'
            });

        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    });