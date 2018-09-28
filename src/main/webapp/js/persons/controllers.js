'use strict';
personsModule.controller('PersonsController', function ($scope, $location, PersonsService, CommonService) {
    $scope.persons = [];
    getPersons();

    $scope.dropdown = {
        availableOptions: [3, 5, 10],
        itemsByPage: 10
    };

    function getPersons() {
        PersonsService.getPersons().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.persons = response;
            }
        });
    }

    $scope.deletePerson = function (person) {
        PersonsService.deletePerson(person.id).then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.persons.splice($scope.persons.indexOf(person), 1);
            }
        });
    };

    $scope.navigateToEdit = function (person) {
        $location.path("/persons/editPerson/" + person.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/persons/editPerson");
    };
}).controller("EditPersonController", function($scope, $location, $routeParams, $filter, PersonsService, CommonService, person) {
    $scope.currentPerson = person;
    getDepartments();
    $scope.pageTitle = $scope.currentPerson.id ? 'PERSON_INFO' : 'PERSON_CREATE';

    $scope.savePerson = function (person) {
        if (angular.isUndefinedOrNull(person.id)) {
            PersonsService.createPerson(person).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/persons");
                }
            });
        } else {
            PersonsService.updatePerson(person).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/persons");
                }
            });
        }
    };

    function getDepartments() {
        CommonService.getEnumValues("Department").then(function (response) {
            $scope.departments = response;
        });
    }
});