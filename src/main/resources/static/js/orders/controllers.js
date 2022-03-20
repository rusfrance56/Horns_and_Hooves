'use strict';
ordersModule.controller('OrdersController', function ($scope, $location, OrdersService, CommonService) {
    $scope.orders = [];
    getOrders();

    function getOrders() {
        OrdersService.getOrders().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.orders = response;
            }
        });
    }

    $scope.deleteOrder = function (order) {
        OrdersService.deleteOrder(order.id).then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.orders.splice($scope.orders.indexOf(order), 1);
            }
        });
    };

    $scope.navigateToEdit = function (order) {
        $location.path("/orders/editOrder/" + order.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/orders/createOrder");
    };
}).controller('EditOrderController', function ($scope, $location, OrdersService, CommonService, order) {
    $scope.currentOrder = order;
    $scope.employees = [];
    $scope.employeesForSelectedDep = [];
    $scope.pageTitle = $scope.currentOrder.id ? 'ORDER_INFO' : 'ORDER_CREATE';

    $scope.saveOrder = function (order) {
        if (angular.isUndefinedOrNull(order.id)) {
            OrdersService.createOrder(order).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/orders");
                }
            });
        } else {
            OrdersService.updateOrder(order).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path("/orders");
                }
            });
        }
    };

    if (!angular.isUndefinedOrNull($scope.currentOrder)) {
        if (angular.isUndefinedOrNull($scope.currentOrder.dueDate)) {
            $scope.currentOrder.dueDate = new Date();
        } else {
            $scope.currentOrder.dueDate = new Date($scope.currentOrder.dueDate);
        }
        $scope.currentOrder.dueDate.setHours($scope.currentOrder.dueDate.getHours(), $scope.currentOrder.dueDate.getMinutes(), 0, 0);
    }
});