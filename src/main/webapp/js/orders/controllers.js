'use strict';
ordersModule.controller('OrdersController', function ($scope, $location, OrdersService) {
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
        $location.path("/orders/editOrder");
    };
}).controller('EditOrderController', function ($scope, $location, OrdersService, order) {
    $scope.currentOrder = order;
    $scope.employees = [];
    $scope.employeesForSelectedDep = [];
    $scope.pageTitle = $scope.currentOrder.id ? 'ORDER_INFO' : 'ORDER_CREATE';

    getEmployees();

    function getEmployees() {
        /*EmployeeService.getEmployees().then(function (employees) {
            $scope.employees = employees;
            filterEmpByDep();
            if (!angular.isUndefinedOrNull($scope.currentOrder.employee)) {
                $scope.currentOrder.employee = $scope.employeesForSelectedDep.filter(function (emp) {
                    return emp.id === $scope.currentOrder.employee.id;
                })[0];
            }
        });*/
    }

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

    /*
    function filterEmpByDep() {
        if (Array.isArray($scope.employees) && $scope.employees.length > 0) {
            $scope.employeesForSelectedDep = $scope.employees.filter(function (employee) {
                return employee.department.id === $scope.currentOrder.department.id;
            });
        }
    }*/
    /*$scope.$watch('currentOrder.department', function () {
           filterEmpByDep();
   });*/

    if (!angular.isUndefinedOrNull($scope.currentOrder)) {
        if (angular.isUndefinedOrNull($scope.currentOrder.dueDate)) {
            $scope.currentOrder.dueDate = new Date();
        } else {
            $scope.currentOrder.dueDate = new Date($scope.currentOrder.dueDate);
        }
        $scope.currentOrder.dueDate.setHours($scope.currentOrder.dueDate.getHours(), $scope.currentOrder.dueDate.getMinutes(), 0, 0);
    }
});