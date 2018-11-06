'use strict';
tasksModule.service('TasksService', function ($http) {
   var rootPath = "/tasks/";
    return {
        createTask : function (task) {
            return $http.post(rootPath, task).then(function (response) {
                return response.data;
            })
        },
        updateTasks : function (task) {
            return $http.put(rootPath + task.id, task).then(function (response) {
                return response.data;
            })
        },
        deleteTask : function (id) {
            return $http.delete(rootPath + id).then(function (response) {
                return response.data;
            })
        },
        getTasks : function () {
            return $http.get(rootPath).then(function (response) {
                return response.data;
            })
        },
        getTaskById : function (id) {
            return $http.get(rootPath + id).then(function (response) {
                return response.data;
            })
        }
    }
});