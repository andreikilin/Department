package com.aimprosoft.department.dao.impl;

import com.aimprosoft.department.dao.Dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;

/**
 * Created by merovingien on 3/11/14.
 */
public abstract class AbstractDaoHibernate<E, I extends Serializable> implements Dao<E, I> {

    private Class<E> entityClass;

    @Autowired
    protected SessionFactory sessionFactory;

    protected AbstractDaoHibernate(Class<E> entityClass) {
        this.entityClass = entityClass;

    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public I add(E e) {
        return (I)getCurrentSession().save(e);
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
    public E getByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return (E)criteria.uniqueResult();
    }

    @Override
    public List<E> list() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }
}
