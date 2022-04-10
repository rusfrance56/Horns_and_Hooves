'use strict';
var tasksModule = angular.module('tasksModule', [
    'ngRoute',
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common'
]);

tasksModule.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {
    $locationProvider.hashPrefix('');
    $routeProvider.when('/tasks/editTask/:id', {
        templateUrl: 'views/tasks/viewTasks.html',
        controller: 'TasksController',
        resolve: {
            task: function (TasksService, $route, CommonService, $location, EditTaskModalService) {
                return TasksService.getTaskById($route.current.params.id).then(function (response) {
                    EditTaskModalService.openEditTaskModal(response.task);
                    return response.task;
                }, function (response) {
                    CommonService.openMessageModal('danger', response.errorMessage, 'big_modal');
                    $location.path('/tasks/createTask');
                });
            }
        }
    }).when('/tasks/createTask', {
        templateUrl: 'views/tasks/viewTasks.html',
        controller: 'TasksController',
        resolve: {
            task: function (EditTaskModalService) {
                EditTaskModalService.openEditTaskModal({});
                return {};
            }
        }
    }).when('/tasks', {
        templateUrl: 'views/tasks/viewTasks.html',
        controller: 'TasksController'
    }).otherwise({
        templateUrl: 'views/tasks/viewTasks.html',
        controller: 'TasksController'
    })
}]);