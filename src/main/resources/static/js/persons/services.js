'use strict';
personsModule.service('PersonsService', function ($http) {
    var rootPath = '/persons/';
    return {
        createPerson: function (person) {
            return $http.post(rootPath, person).then(function (response) {
                return response.data;
            });
        },
        updatePerson: function (person) {
            return $http.put(rootPath + person.id, person).then(function (response) {
                return response.data;
            });
        },
        deletePerson: function (id) {
            return $http.delete(rootPath + id).then(function (response) {
                return response.data;
            });
        },
        getPersons: function () {
            return $http.get(rootPath).then(function (response) {
                return response.data;
            });
        },
        getPersonById: function (id) {
            return $http.get(rootPath + id).then(function (response) {
                return response.data;
            });
        }
    }
});