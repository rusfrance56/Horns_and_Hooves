ordersModule.service('OrdersService', function ($http) {
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
        createOrder: function (order) {
            return $http.post(rootPath, order).then(function(response) {
                return response.data;
            });
        },
        updateOrder: function (order) {
            order.items = transformObjectToIdArray(order.items);
            return $http.put(rootPath + order.id, order).then(function (response) {
                return response.data;
            });
        },
        deleteOrder: function (orderId) {
            return $http.delete(rootPath + orderId).then(function (response) {
                return response.data;
            });
        },
        getOrders: function () {
            return $http.get(rootPath).then(function (response) {
                return response.data;
            });
        },
        getOrderById: function (id) {
            return $http.get(rootPath + id).then(function (response) {
                return response.data;
            });
        }
    }
});