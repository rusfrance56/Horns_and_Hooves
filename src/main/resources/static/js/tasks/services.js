'use strict';
tasksModule.service('TasksService', function ($http, $q) {
   var rootPath = "/tasks/";
    return {
        saveTask : function (task) {
            let deferred = $q.defer();
            let promise;
            if (task.id) {
                promise = $http.put(rootPath + task.id, task);
            } else {
                promise = $http.post(rootPath, task);
            }
            promise.then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve(response);
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        deleteTask : function (id) {
            let deferred = $q.defer();
            $http.delete(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve(response);
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getTasks : function () {
            let deferred = $q.defer();
            $http.get(rootPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({tasks: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getTaskById : function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({task: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
}).service('EditTaskModalService', function ($uibModal, $log, $route) {
    return {
        openEditTaskModal : function (task) {
            var size = 'md';
            var modalInstance =
                $uibModal.open({
                animation: true,
                templateUrl: '/views/tasks/editTaskModal.html',
                controller: 'EditTaskController',
                size: size,
                backdrop: 'static',
                keyboard: false,
                resolve: {
                    task: function () {
                        if (!angular.isUndefinedOrNull(task)) {
                            return task;
                        } else {
                            return {};
                        }
                    }
                }
            });
            //обработчик кнопки OK и CANCEL
            modalInstance.result.then(function (selectedItem) {}, function () {});
        }
    }
});