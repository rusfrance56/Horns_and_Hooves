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
}).controller("EditPersonController", function($scope, $location, $routeParams, $filter, PersonsService) {
    $scope.currentpersons = {};
    $scope.pageTitle = "";
    // loadpersons();
    // getDepartments();

    /*function loadpersons(){
        if (!angular.isUndefinedOrNull($routeParams.id)){
            var result = $.grep($scope.persons, function(e){ return e.id == $routeParams.id; });
            if (result.length > 0) {
                $scope.currentpersons = result[0];
                $scope.pageTitle = $filter('translate')('persons_INFO');
            } else {
                $scope.pageTitle = $filter('translate')('persons_CREATE');
            }
        } else {
            $scope.pageTitle = $filter('translate')('persons_CREATE');
        }
    }*/

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

    /*function getDepartments() {
        personsService.getDepartments().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.departments = response;
            }
        });
    }*/
});