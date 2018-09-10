'use strict';
var ordersModule = angular.module("ordersModule", [
        'ngRoute',
        'smart-table',
        'pascalprecht.translate',
        'ui.bootstrap',
        'Common'
    ]);
ordersModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
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
    });
}]);
ordersModule.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: ' i18n/i18n_',
        suffix: '.json'
    }).registerAvailableLanguageKeys(['en', 'ru'], {
        'en_US': 'en',
        'en_UK': 'en',
        'ru_RU': 'ru'
    }).determinePreferredLanguage().fallbackLanguage('ru');
});