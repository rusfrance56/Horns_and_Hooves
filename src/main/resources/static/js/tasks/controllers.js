'use strict';
tasksModule.controller('TasksController', function ($scope, $state, TasksService, CommonService, UsersService, ItemsService) {
    $scope.tasks = [];
    $scope.users = [];
    $scope.items = [];
    loadData();

    function loadData() {
        UsersService.getUsers().then(function (response) {
            $scope.users = response.users;
            getTasks();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
        ItemsService.getItems().then(function (response) {
            $scope.items = response.items;
            getTasks();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
    }

    function getTasks() {
        TasksService.getTasks().then(function (response) {
            $scope.tasks = response.tasks;
            $scope.tasks.forEach(task => {
                let userForTask = $scope.users.filter(user => user.id === task.userId)[0];
                if (!isUndefinedOrNull(userForTask)) {
                    task.user = userForTask;
                }
                let itemForTask = $scope.items.filter(item => item.id === task.item.id)[0];
                if (!isUndefinedOrNull(itemForTask)) {
                    task.item = itemForTask;
                }
            });
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    }

    $scope.deleteTask = function (task) {
        TasksService.deleteTask(task.id).then(function () {
            $scope.tasks.splice($scope.tasks.indexOf(task), 1);
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    $scope.navigateToEdit = function (task) {
        $state.go('tasks.edit', {id: task.id});
    };
    $scope.navigateToCreate = function () {
        $state.go('tasks.create');
    };
}).controller('EditTaskController', function ($scope, $state, TasksService, CommonService, UsersService, ItemsService,
                                               task, $uibModalInstance) {
    $scope.currentTask = task;
    $scope.pageTitle = $scope.currentTask.id ? 'TASK_INFO' : 'TASK_CREATE';
    $scope.departments = [];
    $scope.statuses = [];
    $scope.priorities = [];
    $scope.users = [];
    $scope.usersByDep = [];
    $scope.items = [];
    $scope.itemsByDep = [];

    loadData();

    $scope.$watch('currentTask.department', function(newValue, oldValue){
        if(newValue !== undefined && newValue !== oldValue) {
            updateUsersByDep();
            updateItemsByDep();
        }
    });

    function updateUsersByDep() {
        $scope.usersByDep = $scope.users.filter(p => angular.equals(p.department, $scope.currentTask.department));
    }

    function updateItemsByDep() {
        $scope.itemsByDep = $scope.items.filter(p => angular.equals(p.department, $scope.currentTask.department));
    }

    function loadData() {
        getDepartments();
        getStatuses();
        getPriorities();
        UsersService.getUsers().then(function (response) {
            $scope.users = response.users;
            updateUsersByDep();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
        ItemsService.getItems().then(function (response) {
            $scope.items = response.items;
            updateItemsByDep();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
    }

    $scope.saveTask = function (task) {
        TasksService.saveTask(task).then(function () {
            $state.transitionTo('tasks', {}, {
                reload: true,
                inherit: false,
                notify: true
            });
        }, function (response) {
            CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
        });
    };

    function getDepartments() {
        CommonService.getEnumValues("Department").then(function (response) {
            $scope.departments = response;
        });
    }
    function getStatuses() {
        CommonService.getEnumValues("TaskStatus").then(function (response) {
            $scope.statuses = response;
        });
    }
    function getPriorities() {
        CommonService.getEnumValues("TaskPriority").then(function (response) {
            $scope.priorities = response;
        });
    }

    if (!isUndefinedOrNull($scope.currentTask)) {
        if (isUndefinedOrNull($scope.currentTask.dueDate)) {
            $scope.currentTask.dueDate = new Date();
        } else {
            $scope.currentTask.dueDate = new Date($scope.currentTask.dueDate);
        }
        $scope.currentTask.dueDate.setHours($scope.currentTask.dueDate.getHours(), $scope.currentTask.dueDate.getMinutes(), 0, 0);
    }

    $scope.ok = function () {
        $scope.saveTask($scope.currentTask);
        $uibModalInstance.close();
    };

    $scope.cancel = function () {
        $state.go('tasks');
        $uibModalInstance.dismiss();
    };
});