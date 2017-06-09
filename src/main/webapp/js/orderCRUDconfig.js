'use strict';
var mainApp = angular.module("mainApp", ['ngRoute']);

/*mainApp.config(['$locationProvider', function($locationProvider) {
    $locationProvider.hashPrefix('');
}]);*/

mainApp.service('OrderService',
    function () {
        var savedData = {}

        function set(data) {
            savedData = data;
        }

        function get() {
            return savedData;
        }

        savedData.getOrders = function(){
            return $http.get("/orders").success(function(response){
                $scope.orders = response;
            });
        };

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


    $http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.selectedDep = $scope.deps[0];
        $scope.refreshEmp();
    });

    $scope.refreshEmp = function(){
        $http.get("/employee/byDep/" + $scope.selectedDep.id).success(function (response) {
            $scope.employees = response;
        });
    }

    $scope.createOrder = function() {
        if ($scope.selectedEmp != null){
            $scope.order.employee_id = $scope.selectedEmp.id;
        }
        $scope.order.department_id = $scope.selectedDep.id;
        $http.post("/orders", $scope.order).success(
            function(response) {
                $location.path("/orders");
            }
        );
    };
});
mainApp.controller('EditOrderController', function ($scope, $http, $location, OrderService){
    $scope.order = OrderService.get();

    $http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.selectedOption = $scope.deps[0];
    });

    $scope.saveOrder = function(order) {
        $http.put("/orders/"+order.id, order).success(
            function(responce) {
                $location.path("/orders");
            });
    }
});



mainApp.controller('OrdersController', function ($scope, $http, $location, OrderService) {
    $scope.myerr = false;
    $scope.setErrors = function(){
        $scope.myerr = true;
    };
    $scope.removeErrors = function () {
        $scope.myerr = false;
    };

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

    $scope.getError = function (error) {
        if (angular.isDefined(error)) {
            if (error.required) {
                return "Поле не должно быть пустым";
            }
        }
    }
});