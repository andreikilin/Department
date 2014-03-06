package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;

/**
 * Created by merovingien on 3/6/14.
 */
public class DepartmentForm {
    private String name;

    public DepartmentForm() {}

    public DepartmentForm(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department saveDepartment() {
        return new Department(name);
    }

}
