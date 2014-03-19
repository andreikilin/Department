package com.aimprosoft.department.entity;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by merovingien on 3/3/14.
 */
@Entity
@Table(name="departments")
public class Department implements BusinessEntity {

    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="name")
    private String name;

    @JsonIgnore
    @OneToMany(targetEntity = Employee.class,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy="department", //mapped with object
            orphanRemoval = true)
    private List<Employee> employeeList;

    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    public Department(Integer id, String name) {
        this.id = id;
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

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Department that = (Department) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
