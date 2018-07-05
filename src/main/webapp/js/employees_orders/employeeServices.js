mainApp.service('EmployeeService', function ($http) {
    var savedData = {};

    return {
        getEmployees: function (pageNumber, size) {
            pageNumber = pageNumber > 0?pageNumber - 1:0;
            return $http.get('/employee/get?page='+pageNumber+'&size='+size).then(function (result) {
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
        setEmployeesToService: function (data) {
            savedData = data;
        },
        getEmployeesFromService: function () {
            return savedData;
        }
    }
});