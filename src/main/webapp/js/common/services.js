common.service('CommonService', function ($uibModal) {

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
});