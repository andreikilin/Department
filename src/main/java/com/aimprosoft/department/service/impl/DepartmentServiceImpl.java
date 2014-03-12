package com.aimprosoft.department.service.impl;

import com.aimprosoft.department.dao.Dao;
import com.aimprosoft.department.dao.DepartmentDao;
import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by merovingien on 3/4/14.
 */
@Service("DepartmentService")
@Transactional
public class DepartmentServiceImpl extends AbstractService<Department> implements DepartmentService {

    @Autowired
    DepartmentDao departmentDao;

    @Override
    Dao<Department> getDao() {
        return departmentDao;
    }

    @Override
    public Department getByName(String name) {
        return departmentDao.getByName(name);
    }

}
