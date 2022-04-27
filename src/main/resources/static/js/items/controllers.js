'use strict';
itemsModule.controller('ItemsController', function ($scope, ItemsService, CommonService, $state) {
    $scope.items = [];
    getItems();

    function getItems() {
        ItemsService.getItems().then(function (response) {
            $scope.items = response.items;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.deleteItem = function (item) {
        ItemsService.deleteItem(item.id).then(function () {
            $scope.items.splice($scope.items.indexOf(item), 1);
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.navigateToEdit = function (item) {
        $state.go("items_edit", {id: item.id});
    };
    $scope.navigateToCreate = function () {
        $state.go("items_create");
    };
}).controller('EditItemController', function ($scope, ItemsService, CommonService, item, $state) {
    $scope.currentItem = item;
    $scope.pageTitle = $scope.currentItem.id ? 'ITEM_INFO' : 'ITEM_CREATE';
    $scope.departments = [];
    loadData();

    function loadData() {
        getDepartments();
    }

    $scope.saveItem = function (item) {
        ItemsService.saveItem(item).then(function () {
            $state.go("items");
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    function getDepartments() {
        CommonService.getEnumValues("Department").then(function (response) {
            $scope.departments = response;
        });
    }
});