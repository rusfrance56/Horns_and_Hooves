'use strict';
itemsModule.service('ItemsService', function ($http, $q, Upload) {
   var rootPath = '/items/';
    return {
        saveItem : function (item) {
            let deferred = $q.defer();
            let promise;
            if (item.id) {
                promise = $http.put(rootPath + item.id, item);
            } else {
                promise = $http.post(rootPath, item);
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
        deleteItem : function (id) {
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
        getItems : function (pagination) {
            let deferred = $q.defer();
            let finalPath = rootPath;
            let isPageRequest = !angular.isUndefinedOrNull(pagination);
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
                            items: response.content,
                            pagination: {
                                totalItems: response.totalElements,
                                currentPage: response.number + 1,
                                itemsPerPage: response.size,
                                availableOptions: pagination.availableOptions
                            }
                        });
                    } else {
                        deferred.resolve({items: response});
                    }
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getItemById : function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({item: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
});