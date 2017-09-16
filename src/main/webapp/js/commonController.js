'use strict';
mainApp.controller('CommonController', function ($scope, $translate) {

    $scope.selectedLang = "ru";

    $scope.$watch('selectedLang', function () {
        $translate.use($scope.selectedLang);
    });
});