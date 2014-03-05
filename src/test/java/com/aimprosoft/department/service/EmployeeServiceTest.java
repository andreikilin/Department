package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static org.junit.Assert.*;


/**
 * Created by merovingien on 3/4/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@Transactional
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    private final int id = 1;
    private final String firstName = "TestName";
    private final String lastName = "TestSurname";
    private final String email = "testemail@emailtest.com";
    private final long inn = 423627;
    private Department department;
    private Employee employee;


    @Before
    public void setUp() throws Exception {
        department = departmentService.getById(2);
        employee = new Employee(id, department, firstName, lastName, email, inn);
        employeeService.add(employee);
    }

    @After
    public void tearDown() throws Exception {
        Employee employeeToDelete = employeeService.getById(id);
        if(employeeToDelete != null)
            employeeService.delete(employeeToDelete);
    }

    @Test
    public void testAdd() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found, employee);
    }

    @Test
    public void testUpdate() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found, employee);

        Employee employeeNew = new Employee(id, department, "Akapulko", lastName, email, 653644);
        employeeService.update(employeeNew);
        Employee foundNew = employeeService.getById(id);
        assertNotNull(foundNew);
        assertEquals(foundNew, employeeNew);
    }

    @Test
    public void testDelete() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found, employee);
        employeeService.delete(found);
        assertNull(employeeService.getById(id));
    }

    @Test
    public void testGetById() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found, employee);
    }

    @Test
    public void testGetByEmail() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found.getEmail(), employee.getEmail());
    }

    @Test
    public void testGetByInn() throws Exception {
        Employee found = employeeService.getById(id);
        assertNotNull(found);
        assertTrue(found.getInn() == employee.getInn());
    }

    @Test
    public void testList() throws Exception {
        assertNotNull(employeeService.list());
        assertTrue(employeeService.list().contains(employee));
    }

    @Test
    public void testListByDepartment() throws Exception {
        Department departmentSoft = departmentService.getById(2);
        assertNotNull(departmentSoft);
        Department departmentDesign = departmentService.getById(3);
        assertNotNull(departmentDesign);

        List<Employee> employeeListSoft = employeeService.listByDepartment(departmentSoft);
        assertNotNull(employeeListSoft);
        assertTrue(employeeListSoft.contains(employee));

        List<Employee> employeeListDesign = employeeService.listByDepartment(departmentDesign);
        assertNotNull(employeeListDesign);
        assertFalse(employeeListDesign.contains(employee));
    }
}
