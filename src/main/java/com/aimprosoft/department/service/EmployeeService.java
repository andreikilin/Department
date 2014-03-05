package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
public interface EmployeeService {
    void add(Employee employee);
    void update(Employee employee);
    void delete(Employee employee);
    Employee getById(int id);
    Employee getByEmail(String email);
    Employee getByInn(long inn);
    List<Employee> list();
    List<Employee> listByDepartment(Department department);
}
