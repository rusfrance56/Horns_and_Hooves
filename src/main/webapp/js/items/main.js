'use strict';
var itemsModule = angular.module('itemsModule', [
    'ngRoute',
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);

itemsModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider.when('/items', {
        templateUrl: 'views/items/viewItems.html',
        controller: 'ItemsController'
    }).when('/items/editItem/:id', {
        templateUrl: 'views/items/editItem.html',
        controller: 'EditItemController',
        resolve: {
            item: function (ItemsService, $route) {
                return ItemsService.getItemById($route.current.params.id);
            }
        }
    }).when('/items/createItem/', {
        templateUrl: 'views/items/editItem.html',
        controller: 'EditItemController',
        resolve: {
            item: function () {
                return {};
            }
        }
    }).otherwise({
        templateUrl: 'views/items/viewItems.html',
        controller: 'ItemsController'
    })
}]);