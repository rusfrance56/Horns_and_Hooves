'use strict';

var services = angular.module('ngdemo.services', ['ngResource']);

var baseUrl = 'http://localhost:8080';

services.factory('OrdersFactory', function ($resource) {
    return $resource(baseUrl + '/orders', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    })
});

services.factory('OrderFactory', function ($resource) {
    return $resource(baseUrl + 'orders/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    })
});
