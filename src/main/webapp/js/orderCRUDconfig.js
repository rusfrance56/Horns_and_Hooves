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
    $scope.filterCondition = {
        dep: 'storage'
    }

    $http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.selectedOpt = $scope.deps[0];
    });

    $scope.changedValue = function(depId) {
        $http.get("/employee/byDep/"+ depId).success(function (response) {
            $scope.employees = response;
        });
    };

    $scope.createOrder = function() {
        $http.post("/orders", $scope.order).success(
            function(response) {
                $location.path("/orders");
            }
        );
    };
    /*$http.get("/departments").success(function (response) {
        $scope.deps = response;
        $scope.selectedDep = $scope.deps[0];
    });
    $http.get("/employee/byDep/" + $scope.selectedDep.id).success(function (response) {
        $scope.employees = response;
        $scope.selectedEmp = "";
    });
    $scope.createOrder = function() {
        $http.post("/orders", $scope.order).success(
            function(response) {
                $location.path("/orders");
            }
        );
    };*/
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
    $http.get("/orders").success(function (response) {
        $scope.orders = response;
    });
        //OrderService.getOrders();


    $scope.editOrder = function (order) {
        OrderService.set(order);
        $location.path("/editOrder");
    };

    $scope.deleteOrder = function (order) {
        $http.delete("/orders/"+order.id).success(function (response) {
            $http.get("/orders").success(function (response) {
                $scope.orders = response;
            });
            //OrderService.getOrders();
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