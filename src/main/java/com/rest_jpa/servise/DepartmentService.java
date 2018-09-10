package com.rest_jpa.servise;

import java.util.List;

public interface DepartmentService {
    Department create(Department department);
    List<Department> findAll();
    Department findById(long id);
    Department update(Department department);
    void delete(long id);
}
