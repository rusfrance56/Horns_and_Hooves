mainApp.service('EmployeeService', function ($http) {
    var savedData = {};

    return {
        getEmployees: function () {
            return $http.get('/employee').then(function (result) {
                return result.data;
            });
        },
        getDepartments: function () {
            return $http.get('/departments').then(function (result) {
                return result.data;
            });
        },
        createEmployee: function (employee) {
            return $http.post("/employee", employee).then(function (result) {
                return result.data;
            });
        },
        updateEmployee: function (employee) {
            return $http.put("/employee/" + employee.id, employee).then(function (result) {
                return result.data;
            });
        },
        deleteEmployee: function (employeeId) {
            return $http.delete('/employee/' + employeeId);
        },
        setEmployee: function (data) {
            savedData = data;
        },
        getEmployee: function () {
            return savedData;
        },
        clearEmployee: function () {
            savedData = {};
        }
    }
});