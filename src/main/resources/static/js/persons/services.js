'use strict';
personsModule.service('PersonsService', function ($http, $q) {
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
            let deferred = $q.defer();
            $http.get(rootPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({persons: response});
                }
            }, function(error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getPersonById: function (id) {
            return $http.get(rootPath + id).then(function (response) {
                return response.data;
            });
        }
    }
});