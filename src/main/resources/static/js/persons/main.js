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
            person: function (PersonsService, $route, CommonService, $location) {
                // PersonsService.getPersonById($stateParams.id)
                return PersonsService.getPersonById($route.current.params.id).then(function (response) {
                    return response.person;
                }, function (response) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                    $location.path("/persons/createPerson");
                });
            }
        }
    }).when('/persons/createPerson', {
        templateUrl: 'views/persons/editPerson.html',
        controller: 'EditPersonController',
        resolve: {
            person: function () {
                return {};
            }
        }
    }).when('/persons', {
        templateUrl: 'views/persons/viewPersons.html',
        controller: 'PersonsController'
    }).otherwise({
        templateUrl: 'views/persons/viewPersons.html',
        controller: 'PersonsController'
    });
}]);