package com.aimprosoft.department.form;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by merovingien on 3/3/14.
 */
public class EmployeeForm {


    private Integer employeeId;
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

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
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
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year.trim();
    }

    public void setDay(String day) {
        this.day = day.trim();
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

    public void loadEmployee(Employee employee) {
        employeeId = employee.getId();
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
