package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;

/**
 * Created by merovingien on 3/4/14.
 */
public interface DepartmentDao extends Dao<Department, Integer> {
//    Integer add(Department department);
//    void update(Department department);
//    void delete(Department department);
    Department getById(Integer id);
    Department getByName(String name);
//    List<Department> list();
}
