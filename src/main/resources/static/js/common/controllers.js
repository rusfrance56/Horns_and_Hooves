'use strict';
common.controller('messageModalCtrl', function ($scope, $uibModalInstance, mode, message) {
    $scope.mode = mode;
    $scope.message = message;

    $scope.ok = function () {
        $uibModalInstance.dismiss();
    };
});
common.controller('CommonController', function ($scope, $translate) {
    $scope.selectedLang = "ru";
    $scope.$watch('selectedLang', function () {
        $translate.use($scope.selectedLang);
    });

    angular.isUndefinedOrNull = function(val) {
        return angular.isUndefined(val) || val === null
    };
});