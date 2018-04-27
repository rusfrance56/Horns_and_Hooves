mainApp.service('OrderService', function ($http) {
    var savedData = {};

    return {
        getOrders: function () {
            return $http.get('/orders').then(function (result) {
                return result.data;
            });
        },
        createOrder: function (order) {
            return $http.post("/orders", order).then(function(result) {
                return result.data;
            });
        },
        updateOrder: function (order) {
            return $http.put("/orders/" + order.id, order).then(function (result) {
                return result.data;
            });
        },
        deleteOrder: function (orderId) {
            return $http.delete('/orders/' + orderId);
        },
        setOrdersToService: function (data) {
            savedData = data;
        },
        getOrdersFromService: function () {
            return savedData;
        }
    }
});