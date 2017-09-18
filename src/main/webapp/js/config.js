'use strict';
var mainApp = angular.module("mainApp", ['ngRoute', 'smart-table', 'pascalprecht.translate']);

mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider.when('/orders/addOrder', {
        templateUrl: 'views/order/addOrder.html',
        controller: 'CreateUpdateOrderController'
    }).when('/orders/editOrder/:orderId', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'CreateUpdateOrderController'
    }).when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).otherwise({
        redirectTO: '/orders'
    });
}]);
mainApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.when('/employee/addEmp', {
            templateUrl: 'views/employee/addEmployee.html',
            controller: 'CreateUpdateEmpController'
        }).when('/employee/editEmp', {
            templateUrl: 'views/employee/editEmployee.html',
            controller: 'CreateUpdateEmpController'
        }).when('/employee', {
            templateUrl: 'views/employee/viewEmployee.html',
            controller: 'EmpsController'
        }).otherwise({
            redirectTO: '/employee'
        });
    }
]);
mainApp.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: ' i18n/i18n_',
        suffix: '.json'
    }).registerAvailableLanguageKeys(['en', 'ru'], {
        'en_US': 'en',
        'en_UK': 'en',
        'ru_RU': 'ru'
    }).determinePreferredLanguage().fallbackLanguage('ru');
});