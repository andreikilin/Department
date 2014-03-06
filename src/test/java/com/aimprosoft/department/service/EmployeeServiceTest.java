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


import java.util.Date;
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

    private Integer id;
    private final String firstName = "TestName";
    private final String lastName = "TestSurname";
    private final String email = "testemail@emailtest.com";
    private final Date birthday = new Date(2011,2,12);
    private final Long inn = 423627L;
    private int departmentIdTest1;
    private int departmentIdTest2;
    private Department departmentTest1;
    private Department departmentTest2;
    private Employee employee;


    @Before
    public void setUp() throws Exception {
        departmentIdTest1 = departmentService.add(new Department("TestDepartment1"));
        departmentTest1 = departmentService.getById(departmentIdTest1);
        departmentIdTest2 = departmentService.add(new Department("TestDepartment2"));
        departmentTest2 = departmentService.getById(departmentIdTest2);
        id = employeeService.add(new Employee(departmentTest1, firstName, lastName, email, birthday, inn));
        employee = employeeService.getById(id);
     }

    @After
    public void tearDown() throws Exception {
        Employee employeeToDelete = employeeService.getById(id);
        if(employeeToDelete != null)
            employeeService.delete(employeeToDelete);
        Department departmentTest1ToDelete = departmentService.getById(departmentIdTest1);
        if(departmentTest1ToDelete != null)
            departmentService.delete(departmentTest1ToDelete);
        Department departmentTest2ToDelete = departmentService.getById(departmentIdTest2);
        if(departmentTest2ToDelete != null)
            departmentService.delete(departmentTest2ToDelete);
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

        employee.setInn(653644L);
        employee.setFirstName("Akapulko");
        employeeService.update(employee);
        found = employeeService.getById(id);
        assertNotNull(found);
        assertEquals(found, employee);
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
        Employee found = employeeService.getByInn(inn);
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
        List<Employee> employeeListTest1 = employeeService.listByDepartment(departmentTest1);
        assertNotNull(employeeListTest1);
        assertTrue(employeeListTest1.contains(employee));

        List<Employee> employeeListTest2 = employeeService.listByDepartment(departmentTest2);
        assertNotNull(employeeListTest2);
        assertFalse(employeeListTest2.contains(employee));
    }
}
