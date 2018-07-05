'use strict';
var mainApp = angular.module("mainApp", [
        'ngRoute',
        'smart-table',
        'pascalprecht.translate',
        'ui.bootstrap', 'ui.grid',
        'ui.grid.pagination',
        'Common'
    ]);
mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
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
    }).when('/employee/editEmp/:id', {
        templateUrl: 'views/employee/editEmployee.html',
        controller: 'EditEmpController'
    }).when('/employee/editEmp', {
        templateUrl: 'views/employee/editEmployee.html',
        controller: 'EditEmpController'
    }).when('/employee', {
        templateUrl: 'views/employee/viewEmployee.html',
        controller: 'EmpsController'
    }).otherwise({
        // redirectTO: '/employee'
        templateUrl: 'views/employee/viewEmployee.html',
        controller: 'EmpsController'
    });
}]);
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