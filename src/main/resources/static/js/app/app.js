(function () {
    'use strict';

    angular
        .module('app', ['ui.router'])
        .config(config)
        .run(run);

    config.$inject = ['$urlRouterProvider', '$stateProvider', '$locationProvider', '$httpProvider'];
    function config($urlRouterProvider, $stateProvider, $locationProvider, $httpProvider) {
        $locationProvider.hashPrefix('');
        $stateProvider
            .state('home', {
                url: '/',
                controller: 'HomeController',
                templateUrl: 'views/home/home.html',
                controllerAs: 'vm'
            })
            .state('login', {
                url: '/login',
                controller: 'LoginController',
                templateUrl: 'views/login/login.html',
                controllerAs: 'vm'
            });
        $urlRouterProvider.otherwise('/login');
        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
    }

    run.$inject = ['$rootScope', '$location', '$http', '$window'];
    function run($rootScope, $location, $http, $window) {
        var userData = $window.sessionStorage.getItem('userData');
        if (userData) {
            $http.defaults.headers.common['Authorization']
                = 'Basic ' + JSON.parse(userData).authData;
        }

        $rootScope
            .$on('$locationChangeStart', function (event, next, current) {
                var restrictedPage
                    = $.inArray($location.path(), ['/login']) === -1;
                var loggedIn
                    = $window.sessionStorage.getItem('userData');
                if (restrictedPage && !loggedIn) {
                    $location.path('/login');
                }
            });
    }
})();