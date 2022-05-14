'use strict';
itemsModule.controller('ItemsController', function ($scope, ItemsService, CommonService, $state, $timeout) {
    $scope.items = [];
    $scope.pagination = {
        currentPage: 1,
        totalItems: null,
        availableOptions: [5, 10, 20],
        itemsPerPage: 5,
        sort: {
            direction: "ASC",
            field: 'created'
        },
        from: 0/*,
        filter: */
    };

    $scope.isLoading = false;
    $scope.initLoad = false;
    init();

    function init() {
        $timeout(function(){
            $scope.initLoad = true;
            let tableState = $scope.ctrl.tableState();
            $scope.updateTable(tableState, $scope.ctrl);
        }, 300);
    }

    $scope.getItems = function () {
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
    }

    $scope.updateTable = function (tableState, ctrl) {
        if(!$scope.ctrl && ctrl){
            $scope.ctrl = ctrl;
        }
        if(!$scope.initLoad){return;}
        $scope.isLoading = true;
        let pagination = tableState.pagination;
        let start = pagination.start || 0;
        let page  = Math.ceil(start/pagination.number) + 1;
        $scope.pagination.from = start;

        $scope.pagination.currentPage = page;
        $scope.pagination.sort.field = tableState.sort.predicate ? tableState.sort.predicate : null;
        let reverse = !isUndefinedOrNull(tableState.sort.reverse) ? tableState.sort.reverse : null;
        if (reverse != null) {
            $scope.pagination.sort.direction = reverse ? "DESC" : "ASC";
        }
        $scope.pagination.filter = tableState.search.predicateObject ? tableState.search.predicateObject.$ : null;

        ItemsService.getItems($scope.pagination).then(function (response) {
            $scope.items = response.items;
            tableState.pagination.numberOfPages = response.pagination.totalPages;
            tableState.pagination.totalItemCount = response.pagination.totalItems;
            $scope.pagination.totalItems = response.pagination.totalItems;
            tableState.pagination.start = start;
            $scope.isLoading = false;
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

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
        if (!isUndefinedOrNull($scope.currentItem.image)) {
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