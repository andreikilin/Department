package com.aimprosoft.department.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by merovingien on 3/3/14.
 */
@Entity
@Table(name="employees")
public class Employee implements BusinessEntity {

    @Id
    @Column(name="id")
    @GeneratedValue //(strategy = GenerationType.TABLE)
    private Integer id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "departmentId")
    private Department department;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name="inn")
    private Long inn;

    public Employee() {}

    public Employee(Department department, String firstName, String lastName, String email, Date birthday, Long inn) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.inn = inn;
    }

    public Employee(Integer id, Department department, String firstName, String lastName, String email, Date birthday, Long inn) {
        this.id = id;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.inn = inn;
    }

    public Long getInn() {
        return inn;
    }

    public void setInn(Long inn) {
        this.inn = inn;
    }

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (!birthday.equals(employee.birthday)) return false;
        if (!department.equals(employee.department)) return false;
        if (!email.equals(employee.email)) return false;
        if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
        if (!id.equals(employee.id)) return false;
        if (!inn.equals(employee.inn)) return false;
        if (!lastName.equals(employee.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + inn.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", department=" + department +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", inn=" + inn +
                '}';
    }
}
