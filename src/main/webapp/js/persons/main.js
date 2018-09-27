'use strict';
var personsModule = angular.module("personsModule", [
    'ngRoute',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
personsModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
    $routeProvider.when('/persons/editPerson/:id', {
        templateUrl: 'views/persons/editPerson.html',
        controller: 'EditPersonController',
        resolve: {
            person: function (PersonsService, $stateParams) {
                // PersonsService.getPersonById($stateParams.id)
                return PersonsService.getPersonById($stateParams.id);
            }
        }
    }).when('/persons/editPerson', {
        templateUrl: 'views/persons/editPerson.html',
        controller: 'EditPersonController'
    }).when('/persons', {
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    }).otherwise({
        // redirectTO: '/persons'
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    });
}]);
personsModule.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: ' i18n/i18n_',
        suffix: '.json'
    }).registerAvailableLanguageKeys(['en', 'ru'], {
        'en_US': 'en',
        'en_UK': 'en',
        'ru_RU': 'ru'
    }).determinePreferredLanguage().fallbackLanguage('ru');
});