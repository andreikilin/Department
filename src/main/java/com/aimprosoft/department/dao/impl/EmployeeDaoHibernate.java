package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.EmployeeDao;
import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
@Repository("EmployeeDao")
public class EmployeeDaoHibernate  extends AbstractDaoHibernate<Employee, Integer> implements EmployeeDao {

    public EmployeeDaoHibernate(){
        super(Employee.class);
    }

//    @Autowired
//    private SessionFactory sessionFactory;
//
//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }

//    @Override
//    public Integer add(Employee employee) {
//        return (Integer)getCurrentSession().save(employee);
////        System.out.println(object.getClass().getName());
//
//    }
//
//    @Override
//    public void update(Employee employee) {
//        getCurrentSession().merge(employee);
//    }
//
//    @Override
//    public void delete(Employee employee) {
//        getCurrentSession().delete(employee);
//    }


    @Override
    public Employee getById(Integer id) {
//        Employee employee = getByCriteria(Restrictions.like("id", id));
//        Employee employee = (Employee) getCurrentSession().get(Employee.class, id);
        return getByCriteria(Restrictions.like("id", id));
    }

    @Override
    public Employee getByEmail(String email) {
//        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
//        criteria.add(Restrictions.like("email", email));
//        return (Employee) criteria.uniqueResult();
//        return getByCriteria(Restrictions.like("email", email));
        return (Employee) getCurrentSession()
                .createQuery("from Employee where email = :email")
                .setString("email", email).uniqueResult();
    }

    @Override
    public Employee getByInn(Long inn) {
//        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
//        criteria.add(Restrictions.like("inn", inn));
//        return (Employee) criteria.uniqueResult();
        return getByCriteria(Restrictions.like("inn", inn));
    }

//    @Override
//    public List<Employee> list() {
//        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
//        return criteria.list();
//    }

    @Override
    public List<Employee> listByDepartment(Department department) {
        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("department", department));
        return criteria.list();
    }
}
