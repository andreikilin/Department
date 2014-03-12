package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.Dao;
import com.aimprosoft.department.entity.BusinessEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by merovingien on 3/11/14.
 */
public abstract class AbstractDaoHibernate<E extends BusinessEntity> implements Dao<E> {

    private Class<E> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    protected AbstractDaoHibernate(Class<E> entityClass) {
        this.entityClass = entityClass;

    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public Integer add(E e) {
        return (Integer)getCurrentSession().save(e);
    }

    @Override
    public void update(E e) {
        getCurrentSession().merge(e);
    }

    @Override
    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E getById(Integer id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    @Override
    public List<E> list() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }
}
