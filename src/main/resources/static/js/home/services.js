'use strict';
mainApp.service('AuthService', function ($http, $q) {

    return {
        login: function (credentials) {
            $http.get('/user', {
                headers: {
                    authorisation: "Basic "
                        + btoa(credentials.username
                            + ":" + credentials.password)
                }
            }).then(function (response) {
                return response.data;
            });
        },
        logout: function () {
            $http.post('/logout');
        }
    }
});