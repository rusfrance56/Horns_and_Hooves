var mainApp = angular.module("mainApp", ['ngRoute']);

var orderService = mainApp.service('OrderService',
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
mainApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
        when('/addOrder', {
            templateUrl: 'addOrder.htm',
            controller: 'AddOrderController'
        }).
        when('/editOrder', {
            templateUrl: 'editOrder.htm',
            controller: 'EditOrderController'
        }).
        when('/orders', {
            templateUrl: 'viewOrders.htm',
            controller: 'OrdersController'
        }).
        otherwise({
            redirectTO: '/orders'
        });
    }
]);
mainApp.controller('AddOrderController', function ($scope, $http, $location){
    $scope.createOrder = function() {
        $http.post("/orders", $scope.order).success(
            function(response) {
                $location.path("/orders");
            }
        );
    };
});
mainApp.controller('EditOrderController', function ($scope, $http, $location, OrderService){
    $scope.order = OrderService.get();

    $scope.saveOrder = function(order) {
        $http.put("/orders/"+order.id, order).success(
            function(responce) {
                $location.path("/orders");
            });
    }
});
mainApp.controller('OrdersController', function ($scope, $http, $location, OrderService) {
    $http.get("/orders").success(function (response) {
        $scope.orders = response;
    });
    $scope.editOrder = function (order) {
        OrderService.set(order);
        $location.path("/editOrder");
    };

    $scope.deleteOrder = function (order) {
        $http.delete("/orders/"+order.id).success(function (response) {
            $http.get("/orders").success(function (response) {
                $scope.orders = response;
            });
            $location.path("/orders");
        });
    };

    $scope.ordersByDep = function (dep) {
        $http.get("/orders/byDep/"+dep).success(function (response) {
            $scope.orders = response;
            $location.path("/orders");
        });
    };
});

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
mainApp.controller('EmpsController', function ($scope, $http, $location, EmpService) {
    $http.get("/employee").success(function (response) {
        $scope.emps = response;
    });
    $scope.editEmp = function (emp) {
        EmpService.set(emp);
        /*$scope.orders = orderService.*/
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