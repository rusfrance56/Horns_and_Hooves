'use strict';
mainApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.when('/addEmp', {
            templateUrl: 'views/employee/addEmployee.html',
            controller: 'CreateUpdateEmpController'
        }).when('/editEmp', {
            templateUrl: 'views/employee/editEmployee.html',
            controller: 'CreateUpdateEmpController'
        }).when('/employee', {
            templateUrl: 'views/employee/viewEmployee.html',
            controller: 'EmpsController'
        }).otherwise({
            redirectTO: '/employee'
        });
    }
]);
mainApp.controller('CreateUpdateEmpController', function ($scope, $location, EmployeeService) {
    $scope.employee = EmployeeService.getEmployee();
    $scope.departments = [];
    $scope.selectedDep = null;
    getDepartments();

    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            $scope.selectedDep = $scope.departments.filter(function(dep) {
                return dep.id == $scope.employee.departmentId;
            })[0];
            if (angular.isUndefined($scope.selectedDep) || null == $scope.selectedDep) {
                $scope.selectedDep = $scope.departments[0];
            }
        });
    }
//todo возможно просто сделать все в одном контроллере не надо будет хранить рабочего в сервисе и удобнее обращаться к списку работников
//todo сделать выгрузку всех работников и по изменению депертамента просто фильтровать и выдавать их не обращаясь к бд
    $scope.changedValue = function (depId) {
        $http.get("/employee/byDep/" + depId).success(function (response) {
            $scope.employees = response;
        });
    };
    $scope.createEmployee = function () {
        $scope.employee.departmentId = $scope.selectedDep.id;
        EmployeeService.createEmployee($scope.employee).then(function () { //todo разобраться когда надо then or success
                $location.path("/employee");
            }
        );
    };
    /*$scope.saveEmp = function (emp) {
        $http.put("/employee/" + emp.id, emp).success(function () {
                $location.path("/employee");
        });
    }*/
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
        EmployeeService.deleteEmployee(employee.id).success(function () {
            $scope.employees.splice(indexForRemove, 1);
        });
    };

    $scope.editEmployee = function (employee) {
        EmployeeService.setEmployee(employee);
        $location.path("/editEmp");
    };

    $scope.navigateToCreate = function () {
        EmployeeService.clearEmployee();
        $location.path("/addEmp");
    }
});