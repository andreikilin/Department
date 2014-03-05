package com.aimprosoft.department.entity;


import javax.persistence.*;

/**
 * Created by merovingien on 3/3/14.
 */
@Entity
@Table(name="employees")
public class Employee {

    @Id
    @Column(name="id")
    @GeneratedValue //(strategy = GenerationType.TABLE)
    private int id;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "departmentId")
    private Department department;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="inn")
    private long inn;

    public Employee() {

    }

    public Employee(Department department, String firstName, String lastName, String email, long inn) {
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.inn = inn;
    }

    public Employee(int id, Department department, String firstName, String lastName, String email, long inn) {
        this.id = id;
        this.department = department;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.inn = inn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public long getInn() {
        return inn;
    }

    public void setInn(long phone) {
        this.inn = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (id != employee.id) return false;
        if (inn != employee.inn) return false;
        if (!department.equals(employee.department)) return false;
        if (!email.equals(employee.email)) return false;
        if (!firstName.equals(employee.firstName)) return false;
        if (!lastName.equals(employee.lastName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + department.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int) (inn ^ (inn >>> 32));
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
                ", phone=" + inn +
                '}';
    }
}
