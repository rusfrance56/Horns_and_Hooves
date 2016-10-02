var mainApp = angular.module("mainApp", ['ngRoute']);
mainApp.service('OrderService',
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
            $location.path("/orders");
            /* $scope.orders.splice(id, 1);*/
        });
    };
});