package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.EmployeeDao;
import com.aimprosoft.department.entity.Department;
import com.aimprosoft.department.entity.Employee;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by merovingien on 3/4/14.
 */
@Repository("EmployeeDao")
public class EmployeeDaoHibernate  extends AbstractDaoHibernate<Employee> implements EmployeeDao {

    public EmployeeDaoHibernate(){
        super(Employee.class);
    }

    @Override
    public Employee getByEmail(String email) {
        return (Employee) getCurrentSession()
                .createQuery("from Employee where email = :email")
                .setString("email", email).uniqueResult();
    }

    @Override
    public Employee getByInn(Long inn) {
        return (Employee) getCurrentSession()
                .createQuery("from Employee where inn = :inn")
                .setLong("inn", inn).uniqueResult();
    }

    @Override
    public List<Employee> listByDepartment(Department department) {
        Criteria criteria = getCurrentSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("department", department));
        return criteria.list();
    }
}
