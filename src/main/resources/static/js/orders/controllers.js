'use strict';
ordersModule.controller('OrdersController', function ($scope, OrdersService, CommonService, $state) {
    $scope.orders = [];
    getOrders();

    function getOrders() {
        OrdersService.getOrders().then(function (response) {
            $scope.orders = response.orders;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.deleteOrder = function (order) {
        OrdersService.deleteOrder(order.id).then(function () {
            $scope.orders.splice($scope.orders.indexOf(order), 1);
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.navigateToEdit = function (order) {
        $state.go('orders_edit', {id: order.id});
    };
    $scope.navigateToCreate = function () {
        $state.go('orders_create');
    };
}).controller('EditOrderController', function ($scope, OrdersService, CommonService, order, $state, ItemsService) {
    $scope.currentOrder = order;
    $scope.employees = [];
    $scope.employeesForSelectedDep = [];
    $scope.pageTitle = $scope.currentOrder.id ? 'ORDER_INFO' : 'ORDER_CREATE';

    loadItems();

    function loadItems() {
        ItemsService.getItems().then(function (response) {
            $scope.items = response.items;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.saveOrder = function (order) {
        OrdersService.saveOrder(order).then(function () {
            $state.go('orders');
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.addItemsToOrder = function () {
        if (isUndefinedOrNull($scope.currentOrder.items)) {
            $scope.currentOrder.items = [];
        }
        $scope.currentOrder.items.push(...$scope.items.filter(i => i.check));
        $scope.items.forEach(i => i.check = false);
    };

    $scope.calculateTotal = function () {
        if (isUndefinedOrNull($scope.currentOrder.items)) {
            return 0;
        }
        return $scope.currentOrder.items.reduce(
            (accumulator, current) => accumulator + Number(current["price"]), 0
        );
    };

    if (!isUndefinedOrNull($scope.currentOrder)) {
        if (isUndefinedOrNull($scope.currentOrder.dueDate)) {
            $scope.currentOrder.dueDate = new Date();
        } else {
            $scope.currentOrder.dueDate = new Date($scope.currentOrder.dueDate);
        }
        $scope.currentOrder.dueDate.setHours($scope.currentOrder.dueDate.getHours(), $scope.currentOrder.dueDate.getMinutes(), 0, 0);
    }
});