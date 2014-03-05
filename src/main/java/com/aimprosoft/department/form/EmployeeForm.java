package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import com.aimprosoft.department.service.DepartmentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.beancontext.BeanContext;

/**
 * Created by merovingien on 3/3/14.
 */
@Component
public class EmployeeForm {


    private Department department;
    private Long inn;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeForm() {

    }

    public EmployeeForm(Department department, long inn, String firstName, String lastName, String email) {
        this.department = department;
        this.inn = inn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeForm that = (EmployeeForm) o;

        if (!department.equals(that.department)) return false;
        if (!email.equals(that.email)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!inn.equals(that.inn)) return false;
        if (!lastName.equals(that.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = department.hashCode();
        result = 31 * result + inn.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }

    public Employee saveEmployee() {
        return new Employee(department, firstName, lastName, email, inn);
    }
}
