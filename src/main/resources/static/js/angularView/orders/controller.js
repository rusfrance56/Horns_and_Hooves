'use strict';

var app = angular.module('ngdemo.controllers', []);

app.run(function ($rootScope, $templateCache) {
    $rootScope.$on('$viewContentLoaded', function () {
        $templateCache.removeAll();
    });
});

app.controller('OrdersCtrl', [$scope, $location, $filter,
    OrderFactory, OrdersFactory,
    function($scope, OrderFactory, OrdersFactory, $location) {

        $scope.orders = OrdersFactory.query();

        $scope.createOrder = function() {
            $location.path('/addOrder');
        };

        $scope.editOrder = function(orderId) {
            $location.path('/editOrder/' + orderId);
        };

        $scope.deleteOrder = function(orderId) {
            OrderFactory.delete({id: orderId});
            $scope.orders = OrdersFactory.query();
        };
}]);

app.controller('OrderDetailCtrl', ['$scope', '$routeParams',
    'OrderFactory', '$location',
    function ($scope, $routeParams, OrderFactory, $location) {

        $scope.updateOrder = function () {
            OrderFactory.update($scope.order);
            $location.path('/orders');
        };

        $scope.cancel = function () {
            $location.path('/orders');
        };

        $scope.order = OrderFactory.show({id: $routeParams.id});
    }]);

app.controller('OrderCreationCtrl', ['$scope', 'OrdersFactory', '$location',
    function ($scope, OrdersFactory, $location) {

        $scope.createNewOrder = function () {
            OrdersFactory.create($scope.order);
            $location.path('/orders');
        }
    }]);
