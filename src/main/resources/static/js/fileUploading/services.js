'use strict';
fileUploadingModule.service('FileUploadingService', function ($http, $q, Upload) {
    var rootPath = '/files/';

    return {
        saveFile : function (file) {
            let deferred = $q.defer();
            let promise = Upload.upload({
                method: "POST",
                url: rootPath,
                data: {
                    file: file
                }
            })
            promise.then(function (response) {
                response = response.data;
                if (response.error) {
                    deferred.reject(response);
                } else {
                    deferred.resolve(response.response);
                }
            }, function (error) {
                deferred.reject(error);
            });
            return deferred.promise;
        },
        getFileByName : function (fileName) {
            let deferred = $q.defer();
            if (!isUndefinedOrNull(fileName)) {
                $http.get(rootPath + fileName, {responseType: "blob"}).then(function (response) {
                    if (response.error) {
                        deferred.reject(response);
                    } else {
                        deferred.resolve({image: response.data});
                    }
                }, function (error) {
                    deferred.reject(error);
                });
            } else {
                deferred.reject();
            }
            return deferred.promise;
        }
    }
});