package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;

/**
 * Created by merovingien on 3/6/14.
 */
public class DepartmentForm {

    private Integer id;
    private String name;

    public DepartmentForm() {}

    public DepartmentForm(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department saveDepartment() {
        return new Department(id, name);
    }

    public Department updateDepartment(Department department){
        department.setId(id);
        department.setName(name);
        return department;
    }

    public void loadDepartment(Department department) {
        id = department.getId();
        name = department.getName();
    }

}
