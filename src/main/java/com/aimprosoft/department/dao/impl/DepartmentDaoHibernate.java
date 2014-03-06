package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.DepartmentDao;
import com.aimprosoft.department.entity.Department;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
@Repository("DepartmentDao")
public class DepartmentDaoHibernate implements DepartmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Integer add(Department department) {
         return (Integer)getCurrentSession().save(department);
//        System.out.println(departmentReturn);
    }

    @Override
    public void update(Department department) {
        getCurrentSession().merge(department);
    }

    @Override
    public void delete(Department department) {
        getCurrentSession().delete(department);
    }

    @Override
    public Department getById(Integer id) {
        Department found = (Department) getCurrentSession().get(Department.class, new Integer(id));
        return found;
    }

    @Override
    public Department getByName(String name) {
        Criteria criteria = getCurrentSession().createCriteria(Department.class);
        criteria.add(Restrictions.like("name", name));
        return (Department) criteria.uniqueResult();
    }

   @Override
    public List<Department> list() {
       Criteria criteria = getCurrentSession().createCriteria(Department.class);
       return criteria.list();
    }
}
