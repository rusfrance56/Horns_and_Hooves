'use strict';
itemsModule.controller('ItemsController', function ($scope, $location, ItemsService, CommonService) {
    $scope.items = [];
    getItems();

    function getItems() {
        ItemsService.getItems().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.items = response;
            }
        });
    }

    $scope.deleteItem = function (item) {
        ItemsService.deleteItem(item.id).then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.items.splice($scope.items.indexOf(item), 1);
            }
        });
    };

    $scope.navigateToEdit = function (item) {
        $location.path("/items/editItem/" + item.id);
    };
    $scope.navigateToCreate = function () {
        $location.path("/items/createItem");
    };
}).controller('EditItemController', function ($scope, $location, ItemsService, CommonService, item) {
    $scope.currentItem = item;
    $scope.pageTitle = $scope.currentItem.id ? 'ITEM_INFO' : 'ITEM_CREATE';

    $scope.saveItem = function (item) {
        if (angular.isUndefinedOrNull(item.id)) {
            ItemsService.createItem(item).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path('/items');
                }
            });
        } else {
            ItemsService.updateItem(item).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path('/items');
                }
            })
        }
    }
});