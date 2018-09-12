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
                // personsService.setpersonsToService($scope.persons);
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
}).controller("EditPersonController", function($scope, $location, $routeParams, $filter, personsService) {
    // $scope.persons = personsService.getPersonsFromService();
    $scope.currentpersons = {};
    $scope.pageTitle = "";
    // loadpersons();
    // getDepartments();
    // setDepartmentToEmp();

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

   /* $scope.savePersons = function (persons) {
        if (angular.isUndefinedOrNull(persons.id)) {
            personsService.createpersons(persons).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/persons");
                }
            });
        } else {
            personsService.updatepersons(persons).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/persons");
                }
            });
        }
    };*/

    /*function getDepartments() {
        personsService.getDepartments().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.departments = response;
            }
        });
    }*/

    /*function setDepartmentToEmp() {
        if (!angular.isUndefinedOrNull($scope.departments)) {
            if(!angular.isUndefinedOrNull($scope.currentpersons.department)){
                $scope.currentpersons.department = $scope.departments.filter(function (dep) {
                    return dep.id === $scope.currentpersons.department.id;
                })[0]; //todo выйдет за границы если не найдет подходящих департаментов
            } else {
                $scope.currentpersons.department = $scope.departments[0];
            }
        }
    }*/
});