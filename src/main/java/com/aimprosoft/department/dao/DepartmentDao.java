package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;

/**
 * Created by merovingien on 3/4/14.
 */
public interface DepartmentDao extends Dao<Department> {
    Department getByName(String name);
}
