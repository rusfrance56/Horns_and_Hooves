common.controller('messageModalCtrl', function ($scope, $uibModalInstance, mode, message) {
    $scope.mode = mode;
    $scope.message = message;

    $scope.ok = function () {
        $uibModalInstance.dismiss();
    };
});