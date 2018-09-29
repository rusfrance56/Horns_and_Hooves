'use strict';
var personsModule = angular.module("personsModule", [
    'ngRoute',
    'ui.router',
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
            person: function (PersonsService, $route) {
                // PersonsService.getPersonById($stateParams.id)
                return PersonsService.getPersonById($route.current.params.id);
            }
        }
    }).when('/persons/editPerson', {
        templateUrl: 'views/persons/editPerson.html',
        controller: 'EditPersonController',
        resolve: {
            person: function () {
                return {};
            }
        }
    }).when('/persons', {
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    }).otherwise({
        templateUrl: 'views/persons/viewPerson.html',
        controller: 'PersonsController'
    });
}]);