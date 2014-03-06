package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.util.Date;


/**
 * Created by merovingien on 3/3/14.
 */
public class EmployeeForm {


    private Department department;
    private String firstName;
    private String lastName;
    private String email;
    private String day;
    private String month;
    private String year;
    private Long inn;

    private Date birthday;

    public EmployeeForm() {}

    public EmployeeForm(Department department, Long inn, String firstName, String lastName, String email) {
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

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Employee saveEmployee() {
        return new Employee(department, firstName, lastName, email, new Date(month+"/"+day+"/"+year), inn);
    }
}
