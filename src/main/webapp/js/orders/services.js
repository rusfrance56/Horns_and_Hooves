ordersModule.service('OrdersService', function ($http) {
    var rootPath = '/orders/';
    return {
        createOrder: function (order) {
            return $http.post(rootPath, order).then(function(response) {
                return response.data;
            });
        },
        updateOrder: function (order) {
            return $http.put(rootPath + order.id, order).then(function (response) {
                return response.data;
            });
        },
        deleteOrder: function (orderId) {
            return $http.delete(rootPath + orderId);
        },
        getOrders: function () {
            return $http.get(rootPath).then(function (response) {
                return response.data;
            });
        }
    }
});