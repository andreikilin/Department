package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
public interface DepartmentDao {
    void add(Department department);
    void update(Department department);
    void delete(Department department);
    Department getById(int id);
    Department getByName(String name);
    List<Department> list();
}
