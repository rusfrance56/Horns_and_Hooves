personsModule.service('PersonsService', function ($http) {
    var rootPath = '/persons/';
    return {
        createPerson: function (person) {
            return $http.post(rootPath, person).then(function (responce) {
                return responce.data;
            });
        },
        updatePerson: function (person) {
            return $http.put(rootPath + person.id, person).then(function (responce) {
                return responce.data;
            });
        },
        deletePerson: function (id) {
            return $http.delete(rootPath + id);
        },
        getPersons: function () {
            return $http.get(rootPath).then(function (responce) {
                return responce.data;
            });
        },
        getPersonById: function (id) {
            return $http.get(rootPath + id).then(function (responce) {
                return responce.data;
            });
        }
    }
});