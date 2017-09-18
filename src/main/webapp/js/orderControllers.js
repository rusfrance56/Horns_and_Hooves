'use strict';
mainApp.controller('CreateUpdateOrderController', function ($scope, $location, OrderService, EmployeeService) {
    $scope.order = OrderService.getOrder();
    $scope.departments = [];
    $scope.employees = [];
    $scope.selectedDep = {};
    $scope.selectedEmp = {};
    $scope.employeesForSelectedDep = [];
    getDepartments();
    getEmployees();

    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            if (!angular.isUndefinedOrNull($scope.departments)) {
                if (!angular.isUndefinedOrNull($scope.order.department)) {
                    $scope.selectedDep = $scope.departments.filter(function (dep) {
                        return dep.id === $scope.order.department.id;
                    })[0];
                } else {
                    $scope.selectedDep = $scope.departments[0];
                }
            }
        });
    }
    function getEmployees() {
        EmployeeService.getEmployees().then(function (employees) {
            $scope.employees = employees;
            filterEmpByDep();
            if (!angular.isUndefinedOrNull($scope.order.employee)) {
                $scope.selectedEmp = $scope.employeesForSelectedDep.filter(function (emp) {
                    return emp.id === $scope.order.employee.id;
                })[0];
            }
        });
    }
    function filterEmpByDep() {
        if (Array.isArray($scope.employees) && $scope.employees.length > 0) {
            $scope.employeesForSelectedDep = $scope.employees.filter(function (emp) {
                return emp.department.id === $scope.selectedDep.id;
            });
        }
    }
    if (!angular.isUndefinedOrNull($scope.order)) {
        if (angular.isUndefinedOrNull($scope.order.dateTime)) {
            $scope.order.dateTime = new Date();
        } else {
            $scope.order.dateTime = new Date($scope.order.dateTime);
        }
        $scope.order.dateTime.setHours($scope.order.dateTime.getHours(), $scope.order.dateTime.getMinutes(), 0, 0);
    }

    $scope.saveOrder = function () {
        if (angular.isUndefinedOrNull($scope.order.department)) {
            $scope.order.department = {};
        }
        if (angular.isUndefinedOrNull($scope.order.employee)) {
            $scope.order.employee = {};
        }

        $scope.order.department.id = $scope.selectedDep.id;
        $scope.order.employee.id = $scope.selectedEmp.id;

        if (angular.isUndefinedOrNull($scope.order.id)) {
            OrderService.createOrder($scope.order).then(function () {
                $location.path("/orders");
            });
        } else {
            OrderService.updateOrder($scope.order).then(function () {
                $location.path("/orders");
            })
        }
    };

    $scope.$watch('selectedDep', function () {
        filterEmpByDep();
    });
});
mainApp.controller('OrdersController', function ($scope, $location, OrderService) {
    $scope.orders = [];
    getOrders();
    $scope.myerr = false;

    function getOrders() {
        OrderService.getOrders().then(function (orders) {
            $scope.orders = orders;
        });
    }

    $scope.deleteOrder = function (order) {
        var indexForRemove = $scope.orders.indexOf(order);
        OrderService.deleteOrder(order.id).then(function () {
            $scope.orders.splice(indexForRemove, 1);
        });
    };

    $scope.editOrder = function (order) {
        OrderService.setOrder(order);
        $location.path("/orders/editOrder");
    };

    $scope.navigateToCreate = function () {
        OrderService.clearOrder();
        $location.path("/orders/addOrder");
    };

    $scope.setErrors = function () {
        $scope.myerr = true;
    };
    $scope.removeErrors = function () {
        $scope.myerr = false;
    };
    $scope.getError = function (error) {
        if (angular.isDefined(error)) {
            if (error.required) {
                return "Поле не должно быть пустым";
            }
        }
    }
});