'use strict';
var mainApp = angular.module("mainApp", [
    'ui.router',
    'smart-table',
    'pascalprecht.translate',
    'ui.bootstrap',
    'Common',
    'usersModule',
    'ordersModule',
    'itemsModule',
    'tasksModule',
    'fileUploadingModule',
    'ngFileUpload'
]);

mainApp.config(['$locationProvider', function ($locationProvider) {
    $locationProvider.hashPrefix('');
}]);

mainApp.config(function ($translateProvider) {
    $translateProvider.useStaticFilesLoader({
        prefix: ' i18n/i18n_',
        suffix: '.json'
    }).registerAvailableLanguageKeys(['en', 'ru'], {
        'en_US': 'en',
        'en_UK': 'en',
        'ru_RU': 'ru'
    }).determinePreferredLanguage().fallbackLanguage('ru')
    .useSanitizeValueStrategy('escape');
});
