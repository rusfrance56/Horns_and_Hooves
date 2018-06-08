'use strict';
mainApp.controller('EmpsController', function ($scope, $location, EmployeeService, CommonService) {
    $scope.employees = [];
    getEmployees();

    function getEmployees() {
        EmployeeService.getEmployees().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.employees = response;
                EmployeeService.setEmployeesToService($scope.employees);
            }
        });
    }

    $scope.deleteEmployee = function (employee) {
        EmployeeService.deleteEmployee(employee.id).then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.employees.splice($scope.employees.indexOf(employee), 1);
            }
        });
    };

    $scope.navigateToEdit = function (employee) {
        $location.path("/employee/editEmp/" + employee.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/employee/editEmp");
    };
}).controller("EditEmpController", function($scope, $location, $routeParams, $filter, EmployeeService) {
    $scope.employees = EmployeeService.getEmployeesFromService();
    $scope.currentEmployee = {};
    $scope.pageTitle = "";
    loadEmployee();
    getDepartments();
    setDepartmentToEmp();

    function loadEmployee(){
        if (!angular.isUndefinedOrNull($routeParams.id)){
            var result = $.grep($scope.employees, function(e){ return e.id == $routeParams.id; });
            if (result.length > 0) {
                $scope.currentEmployee = result[0];
                $scope.pageTitle = $filter('translate')('EMPLOYEE_INFO');
            } else {
                $scope.pageTitle = $filter('translate')('EMPLOYEE_CREATE');
            }
        } else {
            $scope.pageTitle = $filter('translate')('EMPLOYEE_CREATE');
        }
    }

    $scope.saveEmployee = function (employee) {
        if (angular.isUndefinedOrNull(employee.id)) {
            EmployeeService.createEmployee(employee).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/employee");
                }
            });
        } else {
            EmployeeService.updateEmployee(employee).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/employee");
                }
            });
        }
    };

    function getDepartments() {
        EmployeeService.getDepartments().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.departments = response;
            }
        });
    }

    function setDepartmentToEmp() {
        if (!angular.isUndefinedOrNull($scope.departments)) {
            if(!angular.isUndefinedOrNull($scope.currentEmployee.department)){
                $scope.currentEmployee.department = $scope.departments.filter(function (dep) {
                    return dep.id === $scope.currentEmployee.department.id;
                })[0]; //todo выйдет за границы если не найдет подходящих департаментов
            } else {
                $scope.currentEmployee.department = $scope.departments[0];
            }
        }
    }
});