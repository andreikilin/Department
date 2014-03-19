package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.form.EmployeeJson;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
public interface EmployeeService extends Service<Employee>{
    Employee getByEmail(String email);
    Employee getByInn(Long inn);
    List<Employee> listByDepartment(Department department);
    List<EmployeeJson> listJsonByDepartment(Department department);
    List<EmployeeJson> listJson();
}
