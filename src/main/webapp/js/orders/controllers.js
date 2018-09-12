'use strict';
ordersModule.controller('OrdersController', function ($scope, $location, OrderService) {
    $scope.orders = [];
    getOrders();

    function getOrders() {
        OrderService.getOrders().then(function (orders) {
            $scope.orders = orders;
            OrderService.setOrdersToService(orders);
        });
    }

    $scope.deleteOrder = function (order) {
        OrderService.deleteOrder(order.id).then(function () {
            $scope.orders.splice($scope.orders.indexOf(order), 1);
        });
    };

    $scope.editOrder = function (order) {
        $location.path("/orders/editOrder/" + order.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/orders/editOrder");
    };
}).controller('EditOrderController', function ($scope, $location, $filter, $routeParams, OrderService, PersonsService) {
    $scope.orders = OrderService.getOrdersFromService();
    $scope.currentOrder = {};
    $scope.departments = [];
    $scope.employees = [];
    $scope.employeesForSelectedDep = [];
    $scope.pageTitle = "";

    loadOrder();
    getDepartments();
    getEmployees();

    function loadOrder(){
        if (!angular.isUndefinedOrNull($routeParams.id)){
            var result = $.grep($scope.orders, function(e){ return e.id == $routeParams.id; });
            if (result.length > 0) {
                $scope.currentOrder = result[0];
                $scope.pageTitle = $filter('translate')('ORDER_INFO');
            } else {
                $scope.pageTitle = $filter('translate')('ORDER_CREATE');
            }
        } else {
            $scope.pageTitle = $filter('translate')('ORDER_CREATE');
        }
    }
    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            if (!angular.isUndefinedOrNull($scope.currentOrder.department)) {
                $scope.currentOrder.department = $scope.departments.filter(function (dep) {
                    return dep.id === $scope.currentOrder.department.id;
                })[0];
            } else {
                $scope.currentOrder.department = $scope.departments[0];
            }
        });
    }
    function getEmployees() {
        EmployeeService.getEmployees().then(function (employees) {
            $scope.employees = employees;
            filterEmpByDep();
            if (!angular.isUndefinedOrNull($scope.currentOrder.employee)) {
                $scope.currentOrder.employee = $scope.employeesForSelectedDep.filter(function (emp) {
                    return emp.id === $scope.currentOrder.employee.id;
                })[0];
            }
        });
    }

    $scope.saveOrder = function (order) {
        if (angular.isUndefinedOrNull(order.id)) {
            OrderService.createOrder(order).then(function () {
                $location.path("/orders");
            });
        } else {
            OrderService.updateOrder(order).then(function () {
                $location.path("/orders");
            })
        }
    };

    function filterEmpByDep() {
        if (Array.isArray($scope.employees) && $scope.employees.length > 0) {
            $scope.employeesForSelectedDep = $scope.employees.filter(function (employee) {
                return employee.department.id === $scope.currentOrder.department.id;
            });
        }
    }
    if (!angular.isUndefinedOrNull($scope.currentOrder)) {
        if (angular.isUndefinedOrNull($scope.currentOrder.dateTime)) {
            $scope.currentOrder.dateTime = new Date();
        } else {
            $scope.currentOrder.dateTime = new Date($scope.currentOrder.dateTime);
        }
        $scope.currentOrder.dateTime.setHours($scope.currentOrder.dateTime.getHours(), $scope.currentOrder.dateTime.getMinutes(), 0, 0);
    }

    $scope.$watch('currentOrder.department', function () {
        filterEmpByDep();
    });
});