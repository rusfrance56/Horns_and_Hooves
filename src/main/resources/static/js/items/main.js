'use strict';
var itemsModule = angular.module('itemsModule', [
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
itemsModule.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $stateProvider
        .state('items', {
            url: '/items',
            templateUrl: 'views/items/viewItems.html',
            controller: 'ItemsController'
        })
        .state('items_create', {
            url: '/items/createItem',
            templateUrl: 'views/items/editItem.html',
            controller: 'EditItemController',
            resolve: {
                item: function () {
                    return {};
                }
            }
        })
        .state('items_edit', {
            url: '/items/editItem/:id',
            templateUrl: 'views/items/editItem.html',
            controller: 'EditItemController',
            resolve: {
                item: function (ItemsService, $stateParams, FileUploadingService) {
                    return ItemsService.getItemById($stateParams.id).then(function (response) {
                        let item = response.item;
                        return FileUploadingService.getFileByName(item.imageUrl).then(function (response) {
                            item.image = response.image;
                            item.dataURL = URL.createObjectURL(item.image);
                            return item;
                        }, function (response) {
                            return item;
                        });
                    }, function (response) {
                        CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                        $state.go('items_create');
                    });
                }
            }
        });
    $urlRouterProvider.otherwise('/items');
}]);
