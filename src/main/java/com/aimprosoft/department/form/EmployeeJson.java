package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by merovingien on 3/18/14.
 */
public class EmployeeJson {
    private Integer id;
    private Department department;
    private String firstName;
    private String lastName;
    private String email;
    private String day;
    private String month;
    private String year;
    private Long inn;
    private Date birthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public void setDay(String day) {
        this.day = day;
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

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

    public void loadEmployee(Employee employee) {
        id = employee.getId();
        department = employee.getDepartment();
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        email = employee.getEmail();
        Date employeeBirthday = employee.getBirthday();
        day = (new SimpleDateFormat("dd")).format(employeeBirthday);
        month = (new SimpleDateFormat("M")).format(employeeBirthday);
        year = (new SimpleDateFormat("yyyy")).format(employeeBirthday);
        inn = employee.getInn();
    }
}
