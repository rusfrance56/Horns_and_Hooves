mainApp.service('OrderServices', function($http) {
    var savedData = {};

    return {
        getOrders: function() {
            return $http.get('/orders').then(function (result) {
               return result.data;
            });
        },
        deleteOrder: function (orderId) {
            return $http.delete('/orders/' + orderId);
        }
    }
});