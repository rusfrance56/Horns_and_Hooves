'use strict';
tasksModule.controller('TasksController', function ($scope, $location, TasksService, CommonService, PersonsService) {
    $scope.tasks = [];
    $scope.persons = [];
    $scope.tasks = [];
    loadData();

    function loadData() {
        $scope.persons = PersonsService.getPersons().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.persons = response;
                getTasks();
            }
        });
    }

    function getTasks() {
        TasksService.getTasks().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.tasks = response;
                $scope.tasks.forEach(task => {
                    let personForTask = $scope.persons.filter(person => person.id === task.personId)[0];
                    if (!angular.isUndefinedOrNull(personForTask)) {
                        task.person = personForTask;
                    }
                });
            }
        });
    }

    $scope.deleteTask = function (task) {
        TasksService.deleteTask(task.id).then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.tasks.splice($scope.tasks.indexOf(task), 1);
            }
        });
    };

    $scope.navigateToEdit = function (task) {
        $location.path('/tasks/editTask/' + task.id);
    };
    $scope.navigateToCreate = function () {
        $location.path('/tasks/createTask');
    };
}).controller('EditTaskController', function ($scope, $location, TasksService, CommonService, task, PersonsService) {
    $scope.currentTask = task;
    $scope.pageTitle = $scope.currentTask.id ? 'TASK_INFO' : 'TASK_CREATE';
    $scope.departments = [];
    $scope.statuses = [];
    $scope.priorities = [];
    $scope.persons = [];

    loadData();

    function loadData() {
        getDepartments();
        getStatuses();
        getPriorities();
        $scope.persons = PersonsService.getPersons().then(function (response) {
            if (response.error) {
                CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
            } else {
                $scope.persons = response;
            }
        });
    }

    $scope.saveTask = function (task) {
        if (angular.isUndefinedOrNull(task.id)) {
            TasksService.createTask(task).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path('/tasks');
                }
            })
        } else {
            TasksService.updateTasks(task).then(function (response) {
                if (response.error) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                } else {
                    $location.path('/tasks')
                }
            })
        }
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

    if (!angular.isUndefinedOrNull($scope.currentTask)) {
        if (angular.isUndefinedOrNull($scope.currentTask.dueDate)) {
            $scope.currentTask.dueDate = new Date();
        } else {
            $scope.currentTask.dueDate = new Date($scope.currentTask.dueDate);
        }
        $scope.currentTask.dueDate.setHours($scope.currentTask.dueDate.getHours(), $scope.currentTask.dueDate.getMinutes(), 0, 0);
    }
});