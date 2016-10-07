mainApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/addEmp', {
            templateUrl: 'addEmp.htm',
            controller: 'AddEmpController'
        }).
        when('/editEmp', {
            templateUrl: 'editEmp.htm',
            controller: 'EditEmpController'
        }).
        when('/employee', {
            templateUrl: 'viewEmps.htm',
            controller: 'EmpsController'
        }).
        otherwise({
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
mainApp.controller('AddEmpController', function ($scope, $http, $location){
    $scope.createEmp = function() {
        $http.post("/employee", $scope.emp).success(
            function(response) {
                $location.path("/employee");
            }
        );
    };
});
mainApp.controller('EditEmpController', function ($scope, $http, $location, EmpService){
    $scope.emp = EmpService.get();
    $scope.saveEmp = function(emp) {
        $http.put("/employee/"+emp.id, emp).success(
            function(responce) {
                $location.path("/employee");
            });
    }
});
mainApp.controller('EmpsController', function ($scope, $http, $location, EmpService/*, OrderService*/) {
    $http.get("/employee").success(function (response) {
        $scope.emps = response;
    });
    $scope.editEmp = function (emp) {
        EmpService.set(emp);
        /*$scope.emp_orders = OrderService.findByDep("storage");*/
        $location.path("/editEmp");
    };

    $scope.deleteEmp = function (emp) {
        $http.delete("/employee/"+emp.id).success(function (response) {
            $http.get("/employee").success(function (response) {
                $scope.emps = response;
            });
            $location.path("/employee");
        });
    };
});