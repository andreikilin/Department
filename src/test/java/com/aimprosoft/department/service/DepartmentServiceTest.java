package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.service.DepartmentService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by merovingien on 3/4/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
@Transactional
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    private int id;
    private final String name = "TestDepartment";
    private Department department;

    @Before
    public void setUp() throws Exception {
        id = departmentService.add(new Department(name));
        department = departmentService.getById(id);
    }

    @After
    public void tearDown() throws Exception {
        Department departmentToDelete = departmentService.getById(id);
        if(departmentToDelete != null)
            departmentService.delete(departmentToDelete);
    }

    @Test
    public void testAdd() {
        Department found = departmentService.getById(id);
        assertNotNull(found);
        assertEquals(found, department);
    }

    @Test
    public void testGetById() {
        Department found = departmentService.getById(id);
        assertNotNull(found);
        assertEquals(found, department);
    }

    @Test
    public void testGetByName() {
        Department found = departmentService.getByName(name);
        assertEquals(found.getName(), department.getName());
    }

    @Test
    public void testList() {
        assertNotNull(departmentService.list());
        assertTrue(departmentService.list().contains(department));
    }

    @Test
    public void testUpdate() {
        Department found = departmentService.getById(id);
        assertNotNull(found);
        assertEquals(found, department);

        Department departmentNew = new Department(id, "NewTestName");
        departmentService.update(departmentNew);
        Department foundNew = departmentService.getById(id);
        assertNotNull(foundNew);
        assertEquals(foundNew, departmentNew);
    }

    @Test
    public void testDelete() {
        Department found = departmentService.getById(id);
        assertNotNull(found);
        assertEquals(found, department);
        departmentService.delete(department);
        assertNull(departmentService.getById(id));
    }

}

