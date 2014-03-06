package com.aimprosoft.department.service.impl;

import com.aimprosoft.department.dao.DepartmentDao;
import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
@Service("DepartmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    public Integer add(Department department) {
        return departmentDao.add(department);
    }

    @Override
    public void update(Department department) {
        departmentDao.update(department);
    }

    @Override
    public void delete(Department department) {
        departmentDao.delete(department);
    }

    @Override
    public Department getById(Integer id) {
        return departmentDao.getById(id);
    }

    @Override
    public Department getByName(String name) {
        return departmentDao.getByName(name);
    }

    @Override
    public List<Department> list() {
        return departmentDao.list();
    }
}
