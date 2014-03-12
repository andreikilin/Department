package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;

/**
 * Created by merovingien on 3/4/14.
 */
public interface DepartmentService extends Service<Department>{
    Department getById(Integer id);
    Department getByName(String name);
}
