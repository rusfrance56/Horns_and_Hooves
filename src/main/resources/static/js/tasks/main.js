'use strict';
var tasksModule = angular.module('tasksModule', [
    'ngRoute',
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);
tasksModule.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.when('', '/tasks');
    $stateProvider
        .state('tasks', {
            url: '/tasks',
            templateUrl: '/views/tasks/viewTasks.html',
            controller: 'TasksController'
        })
        .state('tasks.create', {
            url: '/createTask',
            templateUrl: 'views/tasks/viewTasks.html',
            controller: 'TasksController',
            resolve: {
                task: function (EditTaskModalService) {
                    EditTaskModalService.openEditTaskModal({});
                    return {};
                }
            }
        })
        .state('tasks.edit', {
            url: '/editTask/:id',
            templateUrl: 'views/tasks/viewTasks.html',
            controller: 'TasksController',
            resolve: {
                task: function (TasksService, $stateParams, CommonService, $state, EditTaskModalService) {
                    return TasksService.getTaskById($stateParams.id).then(function (response) {
                        EditTaskModalService.openEditTaskModal(response.task);
                        return response.task;
                    }, function (response) {
                        CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                        $state.go('tasks.create');
                    });
                }
            }
        });
    $urlRouterProvider.otherwise('/tasks');
}]);