'use strict';
usersModule.service('UsersService', function ($http, $q) {
    var rootPath = '/users/';
    return {
        saveUser: function (user) {
            let deferred = $q.defer();
            let promise;
            if (user.id) {
                promise = $http.put(rootPath + user.id, user);
            } else {
                promise = $http.post(rootPath, user);
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
        deleteUser: function (id) {
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
        getUsers: function () {
            let deferred = $q.defer();
            $http.get(rootPath).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({users: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getUserById: function (id) {
            let deferred = $q.defer();
            $http.get(rootPath + id).then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve({user: response});
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        }
    }
});