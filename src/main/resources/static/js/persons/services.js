'use strict';
personsModule.service('PersonsService', function ($http, $q) {
    var rootPath = '/persons/';
    return {
        savePerson: function (person) {
            let deferred = $q.defer();
            let promise;
            if (person.id) {
                promise = $http.put(rootPath + person.id, person);
            } else {
                promise = $http.post(rootPath, person);
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
        deletePerson: function (id) {
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
        getPersons: function () {
            let deferred = $q.defer();
            $http.get(rootPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({persons: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getPersonById: function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({person: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
});