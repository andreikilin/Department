package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
public interface DepartmentDao {
    Integer add(Department department);
    void update(Department department);
    void delete(Department department);
    Department getById(Integer id);
    Department getByName(String name);
    List<Department> list();
}
