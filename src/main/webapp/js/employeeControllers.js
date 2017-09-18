'use strict';
mainApp.controller('CreateUpdateEmpController', function ($scope, $location, EmployeeService) {
    $scope.employee = EmployeeService.getEmployee();
    $scope.departments = [];
    $scope.selectedDep = null;
    getDepartments();

    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            if (!angular.isUndefinedOrNull($scope.departments)) {
                if(!angular.isUndefinedOrNull($scope.employee.department)){
                    $scope.selectedDep = $scope.departments.filter(function (dep) {
                        return dep.id === $scope.employee.department.id;
                    })[0];
                } else {
                    $scope.selectedDep = $scope.departments[0];
                }
            }
        });
    }

//todo возможно просто сделать все в одном контроллере не надо будет хранить рабочего в сервисе и удобнее обращаться к списку работников
    //todo разобраться когда надо then or success
    $scope.saveEmployee = function () {
        if (angular.isUndefinedOrNull($scope.employee.department)) {
            $scope.employee.department = {};
        }
        $scope.employee.department.id = $scope.selectedDep.id;
        if (angular.isUndefinedOrNull($scope.employee.id)) {
            EmployeeService.createEmployee($scope.employee).then(function () {
                $location.path("/employee");
            });
        } else {
            EmployeeService.updateEmployee($scope.employee).then(function () {
                $location.path("/employee");
            });
        }
    };
});
mainApp.controller('EmpsController', function ($scope, $http, $location, EmployeeService) {
    /*$scope.itemsByPage = ['2', '4', '6', '8'];
    $scope.itemByPage = $scope.itemsByPage[1];*/
    $scope.employees = [];
    getEmployees();

    function getEmployees() {
        EmployeeService.getEmployees().then(function (employees) {
            $scope.employees = employees;
        });
    }

    $scope.deleteEmployee = function (employee) {
        var indexForRemove = $scope.employees.indexOf(employee);
        EmployeeService.deleteEmployee(employee.id).then(function () { //todo success
            $scope.employees.splice(indexForRemove, 1);
        });
    };

    $scope.editEmployee = function (employee) {
        EmployeeService.setEmployee(employee);
        $location.path("/employee/editEmp");
    };

    $scope.navigateToCreate = function () {
        EmployeeService.clearEmployee();
        $location.path("/employee/addEmp");
    }
});