'use strict';
common.service('CommonService', function ($uibModal, $http, $translate, $q) {

    this.openMessageModal = function (mode, message, modalClass) {
        let modal = $uibModal.open({
            animation: true,
            templateUrl: '/js/common/views/messageModal.html',
            controller: 'messageModalCtrl',
            keyboard: false,
            backdrop: mode === 'success' ? true : 'static',
            resolve: {
                mode: function () {
                    return mode;
                },
                message: function () {
                    return message;
                }
            },
            windowClass: modalClass
        });
        return modal.result;
    };

    this.getEnumValues = function (enumName) {
        let path = '/enum/get?enumName=' + enumName;
        return $http.get(path).then(function (response) {
            return response.data
        });
    };

    this.translateError = function (response) {
        let errorCode = response.error;
        let errorParams = response.errorParams;
        let errorMessage = response.errorMessage;
        let defer = $q.defer();
        let translateParamsObject = {};
        if (errorParams && errorParams.length > 0) {
            errorParams.forEach(function (element, index) {
                translateParamsObject['value' + (index + 1)] = element;
            });
        }
        $translate(errorCode, translateParamsObject).then(function (translation) {
                defer.resolve((translation === errorCode && errorMessage) ? errorMessage : translation);
            },
            function (a) {
                defer.reject(a);
            });
        return defer.promise;
    };
});