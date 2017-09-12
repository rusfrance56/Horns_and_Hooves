'use strict';
var mainApp = angular.module("mainApp", ['ngRoute', 'smart-table']);

mainApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider.
    when('/orders/addOrder', {
        templateUrl: 'views/order/addOrder.html',
        controller: 'CreateUpdateOrderController'
    }).
    when('/orders/editOrder', {
        templateUrl: 'views/order/editOrder.html',
        controller: 'CreateUpdateOrderController'
    }).
    when('/orders', {
        templateUrl: 'views/order/viewOrders.html',
        controller: 'OrdersController'
    }).
    otherwise({
        redirectTO: '/orders'
    });
}]);
mainApp.controller('CreateUpdateOrderController', function ($scope, $location, OrderService, EmployeeService){
    $scope.order = OrderService.getOrder();
    $scope.departments = [];
    $scope.selectedDep = null;
    $scope.selectedEmp = null;
    getDepartments();
    function getDepartments() {
        EmployeeService.getDepartments().then(function (departments) {
            $scope.departments = departments;
            if (!angular.isUndefined($scope.departments) && null != $scope.departments) {
                $scope.selectedDep = $scope.departments.filter(function (dep) {
                    return dep.id == $scope.order.departmentId;
                })[0];
                if (angular.isUndefined($scope.selectedDep) || null == $scope.selectedDep) {
                    $scope.selectedDep = $scope.departments[0];
                }
            }
        });
    }

    if (null != $scope.selectedDep && !angular.isUndefined($scope.selectedDep.id)
            && null != $scope.selectedDep.id){
        $scope.order.departmentId = $scope.selectedDep.id;
    }
    if (!angular.isUndefined($scope.selectedEmp) && null != $scope.selectedEmp){
        $scope.order.employeeId = $scope.selectedEmp.id;
    }

    $scope.saveOrder = function() {
        if (angular.isUndefined($scope.order.id) || null == $scope.order.id) {
            OrderService.createOrder().then(function () {
                $location.path("/orders");
            });
        } else {
            OrderService.updateOrder($scope.order).then(function () {
                $location.path("/orders");
            })
        }
    };
    /*$scope.selectedDep.$watch('DepartmentChange', function(){
        $http.get("/employee/byDep/" + $scope.selectedDep.id).success(function (response) {
            $scope.employees = response;
        });
    });*/
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

    $scope.setErrors = function(){
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