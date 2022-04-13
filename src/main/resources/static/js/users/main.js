'use strict';
var usersModule = angular.module("usersModule", [
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
usersModule.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $stateProvider
        .state('users', {
            url: '/users',
            templateUrl: 'views/users/viewUsers.html',
            controller: 'UsersController'
        })
        .state('users_create', {
            url: '/users/createUser',
            templateUrl: 'views/users/editUser.html',
            controller: 'EditUserController',
            resolve: {
                user: function () {
                    return {};
                }
            }
        })
        .state('users_edit', {
            url: '/users/editUser/:id',
            templateUrl: 'views/users/editUser.html',
            controller: 'EditUserController',
            resolve: {
                user: function (UsersService, $stateParams, CommonService, $state) {
                    return UsersService.getUserById($stateParams.id).then(function (response) {
                        return response.user;
                    }, function (response) {
                        CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                        $state.go("users_create");
                    });
                }
            }
        });
    $urlRouterProvider.otherwise('/users');
}]);
