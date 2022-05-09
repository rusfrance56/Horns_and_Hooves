'use strict';
itemsModule.controller('ItemsController', function ($scope, ItemsService, CommonService, $state) {
    $scope.items = [];
    $scope.pagination = {
        currentPage: 1,
        totalItems: 10,
        availableOptions: [5, 10, 20],
        itemsPerPage: 5
    };
    getItems();

    $scope.getItems = function () {
        getItems();
    };

    function getItems() {
        ItemsService.getItems($scope.pagination).then(function (response) {
            $scope.items = response.items;
            $scope.pagination = response.pagination;
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
}).controller('EditItemController', function ($scope, ItemsService, CommonService, item, $state, FileUploadingService) {
    $scope.currentItem = item;
    $scope.pageTitle = $scope.currentItem.id ? 'ITEM_INFO' : 'ITEM_CREATE';
    $scope.departments = [];
    loadData();

    $scope.$watch('currentItem.image', function () {
        if (!angular.isUndefinedOrNull($scope.currentItem.image)) {
            $scope.currentItem.dataURL = URL.createObjectURL($scope.currentItem.image);
        }
    });

    function loadData() {
        getDepartments();
    }

    $scope.saveItem = function (item) {
        FileUploadingService.saveFile(item.image).then(function (savedFileName) {
            item.imageUrl = savedFileName;
            ItemsService.saveItem(item).then(function () {
                $state.go("items");
            }, function (response) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            });
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