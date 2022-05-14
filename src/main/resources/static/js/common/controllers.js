'use strict';
common.controller('messageModalCtrl', function ($scope, $uibModalInstance, mode, message) {
    $scope.mode = mode;
    $scope.message = message;

    $scope.ok = function () {
        $uibModalInstance.dismiss();
    };
});
common.controller('CommonController', function ($scope, $translate, $location) {
    $scope.selectedLang = "ru";
    $scope.$watch('selectedLang', function () {
        $translate.use($scope.selectedLang);
    });

    $scope.selectLang = function (lang) {
        $scope.selectedLang = lang;
    };

    $scope.$watch(function(){
        return $location.path();
    }, function(value){
        var pgurl = '#' + value;
        $(".navbar-nav > li > a").each(function () {
            if (pgurl.startsWith($(this).attr("href"))){
                $(this).parent("li").addClass("active");
            } else {
                $(this).parent("li").removeClass("active");
            }
        });
    });
});