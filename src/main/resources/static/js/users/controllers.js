'use strict';
usersModule.controller('UsersController', function ($scope, $state, UsersService, CommonService) {
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
        $state.go("users_edit", {id: user.id});
    };
    $scope.navigateToCreate = function () {
        $state.go("users_create");
    };
}).controller("EditUserController", function($scope, $state, UsersService, CommonService, user) {
    $scope.currentUser = user;
    $scope.departments = [];
    getDepartments();
    $scope.pageTitle = $scope.currentUser.id ? 'USER_INFO' : 'USER_CREATE';

    $scope.saveUser = function (user) {
        UsersService.saveUser(user).then(function () {
            $state.go("users");
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