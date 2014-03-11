package com.aimprosoft.department.dao;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
public interface EmployeeDao extends Dao<Employee, Integer> {
//    Integer add(Employee employee);
//    void update(Employee employee);
//    void delete(Employee employee);
    Employee getById(Integer id);
    Employee getByEmail(String email);
    Employee getByInn(Long inn);
//    List<Employee> list();
    List<Employee> listByDepartment(Department department);

}
