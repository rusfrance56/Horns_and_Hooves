'use strict';
var employeeModule = angular.module("employeeModule", [
    'ngRoute',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
employeeModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
    $routeProvider.when('/employee/editEmp/:id', {
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
employeeModule.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: ' i18n/i18n_',
        suffix: '.json'
    }).registerAvailableLanguageKeys(['en', 'ru'], {
        'en_US': 'en',
        'en_UK': 'en',
        'ru_RU': 'ru'
    }).determinePreferredLanguage().fallbackLanguage('ru');
});