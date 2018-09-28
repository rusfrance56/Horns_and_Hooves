'use strict';
var mainApp = angular.module("mainApp", [
    'ngRoute',
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common',
    'personsModule',
    'ordersModule'
]);

mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
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
