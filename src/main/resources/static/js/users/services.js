'use strict';
usersModule.service('UsersService', function ($http, $q) {
    var rootPath = '/users/';
    return {
        saveUser: function (user) {
            let deferred = $q.defer();
            let promise;
            if (user.id) {
                promise = $http.put(rootPath + user.id, user);
            } else {
                promise = $http.post(rootPath, user);
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
        deleteUser: function (id) {
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
        getUsers: function (pagination) {
            let deferred = $q.defer();
            let finalPath = rootPath;
            let isPageRequest = !isUndefinedOrNull(pagination);
            if (isPageRequest) {
                let page = pagination.currentPage;
                let size = pagination.itemsPerPage;
                page = page - 1;
                finalPath = rootPath + "pagination" + "?page=" + page + "&size=" + size;
            }
            $http.get(finalPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    if (isPageRequest) {
                        deferred.resolve({
                            users: response.content,
                            pagination: {
                                totalItems: response.totalElements,
                                currentPage: response.number + 1,
                                itemsPerPage: response.size,
                                availableOptions: pagination.availableOptions
                            }
                        });
                    } else {
                        deferred.resolve({users: response});
                    }
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getUserById: function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({user: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
});