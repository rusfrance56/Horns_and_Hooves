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
            $scope.persons = response.persons;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.deletePerson = function (person) {
        PersonsService.deletePerson(person.id).then(function () {
            $scope.persons.splice($scope.persons.indexOf(person), 1);
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.navigateToEdit = function (person) {
        $location.path("/persons/editPerson/" + person.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/persons/createPerson");
    };
}).controller("EditPersonController", function($scope, $location, PersonsService, CommonService, person) {
    $scope.currentPerson = person;
    $scope.departments = [];
    getDepartments();
    $scope.pageTitle = $scope.currentPerson.id ? 'PERSON_INFO' : 'PERSON_CREATE';

    $scope.savePerson = function (person) {
        PersonsService.savePerson(person).then(function () {
            $location.path("/persons");
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    function getDepartments() {
        CommonService.getEnumValues("Department").then(function (response) {
            $scope.departments = response;
        });
    }
});