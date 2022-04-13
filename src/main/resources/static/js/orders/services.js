'use strict';
ordersModule.service('OrdersService', function ($http, $q) {
    var rootPath = '/orders/';

    function transformObjectToIdArray(obj) {
        var ids = [];
        obj.forEach(function (elem) {
            if (!angular.isUndefinedOrNull(elem['id'])){
                ids.push(elem['id']);
            }
        });
        return ids;
    }

    return {
        saveOrder : function (order) {
            let deferred = $q.defer();
            let promise;
            if (order.id) {
                order.items = transformObjectToIdArray(order.items);
                promise = $http.put(rootPath + order.id, order);
            } else {
                order.items = transformObjectToIdArray(order.items);
                promise = $http.post(rootPath, order);
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
        deleteOrder : function (id) {
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
        getOrders : function () {
            let deferred = $q.defer();
            $http.get(rootPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({orders: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getOrderById : function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({order: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
});