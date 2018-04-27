'use strict';
mainApp.controller('EmpsController', function ($scope, $location, EmployeeService) {
    $scope.employees = [];
    getEmployees();

    function getEmployees() {
        EmployeeService.getEmployees().then(function (employees) {
            $scope.employees = employees;
            EmployeeService.setEmployeesToService($scope.employees);
        });
    }

    $scope.deleteEmployee = function (employee) {
        EmployeeService.deleteEmployee(employee.id).then(function () {
            $scope.employees.splice($scope.employees.indexOf(employee), 1);
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
            EmployeeService.createEmployee(employee).then(function () {
                $location.path("/employee");
            });
        } else {
            EmployeeService.updateEmployee(employee).then(function () {
                $location.path("/employee");
            });
        }
    };

    //todo изменить вытаскивание департаментов и разделить вытаскивание и засэчивание конкретному работнику
    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            if (!angular.isUndefinedOrNull($scope.departments)) {
                if(!angular.isUndefinedOrNull($scope.currentEmployee.department)){
                    $scope.currentEmployee.department = $scope.departments.filter(function (dep) {
                        return dep.id === $scope.currentEmployee.department.id;
                    })[0];
                } else {
                    $scope.currentEmployee.department = $scope.departments[0];
                }
            }
        });
    }
});