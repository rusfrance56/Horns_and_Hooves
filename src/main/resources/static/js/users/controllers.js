'use strict';
usersModule.controller('UsersController', function ($scope, $location, UsersService, CommonService) {
    $scope.users = [];
    getUsers();

    $scope.dropdown = {
        availableOptions: [3, 5, 10],
        itemsByPage: 10
    };

    function getUsers() {
        UsersService.getUsers().then(function (response) {
            $scope.users = response.users;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.deleteUser = function (user) {
        UsersService.deleteUser(user.id).then(function () {
            $scope.users.splice($scope.users.indexOf(user), 1);
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.navigateToEdit = function (user) {
        $location.path("/users/editUser/" + user.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/users/createUser");
    };
}).controller("EditUserController", function($scope, $location, UsersService, CommonService, user) {
    $scope.currentUser = user;
    $scope.departments = [];
    getDepartments();
    $scope.pageTitle = $scope.currentUser.id ? 'USER_INFO' : 'USER_CREATE';

    $scope.saveUser = function (user) {
        UsersService.saveUser(user).then(function () {
            $location.path("/users");
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    function getDepartments() {
        CommonService.getEnumValues("Department").then(function (response) {
            $scope.departments = response;
        });
    }
});