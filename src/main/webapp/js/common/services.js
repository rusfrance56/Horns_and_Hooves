'use strict';
common.service('CommonService', function ($uibModal, $http) {

    this.openMessageModal = function (mode, message, modalClass) {
        var modal = $uibModal.open({
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
        return $http.post('/enum/get', enumName).then(function (response) {
            return response.data
        });
    };
});