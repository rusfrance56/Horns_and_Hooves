'use strict';
itemsModule.service('ItemsService', function ($http) {
   var rootPath = '/items/';

   return {
       createItem: function (item) {
           return $http.post(rootPath, item).then(function (response) {
              return response.data;
           });
       },
       updateItem: function (item) {
           return $http.put(rootPath + item.id, item).then(function (response) {
               return response.data;
           })
       },
       deleteItem: function (id) {
           return $http.delete(rootPath + id).then(function (response) {
               return response.data;
           });
       },
       getItems: function () {
           return $http.get(rootPath).then(function (response) {
              return response.data;
           });
       },
       getItemById: function (id) {
           return $http.get(rootPath + id).then(function (response) {
               return response.data;
           })
       }
   }
});