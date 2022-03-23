'use strict';
tasksModule.controller('TasksController', function ($scope, $location, TasksService, CommonService, PersonsService) {
    $scope.tasks = [];
    $scope.persons = [];
    $scope.tasks = [];
    loadData();

    function loadData() {
        PersonsService.getPersons().then(function (response) {
            $scope.persons = response.persons;
            getTasks();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
    }

    function getTasks() {
        TasksService.getTasks().then(function (response) {
            $scope.tasks = response.tasks;
            $scope.tasks.forEach(task => {
                let personForTask = $scope.persons.filter(person => person.id === task.personId)[0];
                if (!angular.isUndefinedOrNull(personForTask)) {
                    task.person = personForTask;
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
    $scope.personsByDep = [];

    loadData();

    $scope.$watch('currentTask.department', function(newValue, oldValue){
        if(newValue !== undefined && newValue !== oldValue) {
            updatePersonsByDep();
        }
    });

    function updatePersonsByDep() {
        $scope.personsByDep = $scope.persons.filter(p => angular.equals(p.department, $scope.currentTask.department));
    }

    function loadData() {
        getDepartments();
        getStatuses();
        getPriorities();
        PersonsService.getPersons().then(function (response) {
            $scope.persons = response.persons;
            updatePersonsByDep();
        }, function (response) {
            CommonService.openMessageModal('danger', CommonService.translateError(response), 'big_modal');
        });
    }

    $scope.saveTask = function (task) {
        TasksService.saveTask(task).then(function () {
            $location.path('/tasks');
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

    if (!angular.isUndefinedOrNull($scope.currentTask)) {
        if (angular.isUndefinedOrNull($scope.currentTask.dueDate)) {
            $scope.currentTask.dueDate = new Date();
        } else {
            $scope.currentTask.dueDate = new Date($scope.currentTask.dueDate);
        }
        $scope.currentTask.dueDate.setHours($scope.currentTask.dueDate.getHours(), $scope.currentTask.dueDate.getMinutes(), 0, 0);
    }
});