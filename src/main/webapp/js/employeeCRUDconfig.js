'use strict';
mainApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.when('/addEmp', {
            templateUrl: 'addEmp.htm',
            controller: 'AddEmpController'
        }).when('/editEmp', {
            templateUrl: 'editEmp.htm',
            controller: 'EditEmpController'
        }).when('/employee', {
            templateUrl: 'viewEmps.htm',
            controller: 'EmpsController'
        }).otherwise({
            redirectTO: '/employee'
        });
    }
]);
mainApp.service('EmpService',
    function () {
        var savedData = {}

        function set(data) {
            savedData = data;
        }

        function get() {
            return savedData;
        }

        return {
            set: set,
            get: get
        }
    }
);
mainApp.controller('AddEmpController', function ($scope, $http, $location) {

    $http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.selectedOpt = $scope.deps[0];
    });
    $scope.changedValue = function (depId) {
        $http.get("/employee/byDep/" + depId).success(function (response) {
            $scope.employees = response;
        });
    };
    $scope.createEmp = function () {
        $scope.emp.department_id = $scope.selectedOpt.id;
        $http.post("/employee", $scope.emp).success(
            function (response) {
                $location.path("/employee");
            }
        );
    };
});

mainApp.filter('getById', function () {
    return function (input, id) {
        var i = 0, len = input.length;
        for (; i < len; i++) {
            if (+input[i].id == +id) {
                return input[i];
            }
        }
        return null;
    }
});

mainApp.controller('EditEmpController', function ($scope, $http, $location, EmpService) {
    $scope.emp = EmpService.get();

    $http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.editEmpSelectedDep = $filter('getById')(deps, $scope.emp.department_id);
    });

    $scope.saveEmp = function (emp) {
        $http.put("/employee/" + emp.id, emp).success(
            function (response) {
                $location.path("/employee");
            });
    }
});
mainApp.controller('EmpsController', function ($scope, $http, $location, EmpService/*, OrderService*/) {
    $scope.isFirst = true;
    $scope.isLast = false;
    $scope.totalElements = 99;
    $scope.pageNumber = 0;
    $scope.numberOfElements = 5;


    $scope.recordsOnPageValues = ['2', '4', '6', '8'];
    $scope.recordsOnPage = "4";

    var domen = "/employee/page";

    /*
     "last": true,
     "totalPages": 1,
     "totalElements": 6,
     "size": 20,
     "number": 0,
     "sort": null,
     "first": true,
     "numberOfElements": 6
     */
    $scope.getEmps = function () {
        $http.get(domen + "?" + "size=" + $scope.recordsOnPage).success(function (response) {
            $scope.emps = response.content;
        });
    };

    $scope.getEmps();
    $scope.editEmp = function (emp) {
        EmpService.set(emp);
        /*$scope.emp_orders = OrderService.findByDep("storage");*/
        $location.path("/editEmp");
    };

    $scope.deleteEmp = function (emp) {
        $http.delete("/employee/" + emp.id).success(function (response) {
            $scope.getEmps();
            $location.path("/employee");
        });
    };
});