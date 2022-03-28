'use strict';
var usersModule = angular.module("usersModule", [
    'ngRoute',
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
usersModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.hashPrefix('');
    $routeProvider.when('/users/editUser/:id', {
        templateUrl: 'views/users/editUser.html',
        controller: 'EditUserController',
        resolve: {
            user: function (UsersService, $route, CommonService, $location) {
                // UsersService.getUserById($stateParams.id)
                return UsersService.getUserById($route.current.params.id).then(function (response) {
                    return response.user;
                }, function (response) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                    $location.path("/users/createUser");
                });
            }
        }
    }).when('/users/createUser', {
        templateUrl: 'views/users/editUser.html',
        controller: 'EditUserController',
        resolve: {
            user: function () {
                return {};
            }
        }
    }).when('/users', {
        templateUrl: 'views/users/viewUsers.html',
        controller: 'UsersController'
    }).otherwise({
        templateUrl: 'views/users/viewUsers.html',
        controller: 'UsersController'
    });
}]);