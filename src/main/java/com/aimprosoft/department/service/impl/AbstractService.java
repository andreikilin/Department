package com.aimprosoft.department.service.impl;

import com.aimprosoft.department.dao.Dao;
import com.aimprosoft.department.entity.BusinessEntity;
import com.aimprosoft.department.service.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by merovingien on 3/12/14.
 */
@Transactional
public abstract class AbstractService<E extends BusinessEntity>  implements Service<E> {

    abstract Dao<E> getDao();

    @Override
    public Integer add(E e) {
        return getDao().add(e);
    }

    @Override
    public void update(E e) {
        getDao().update(e);
    }

    @Override
    public void delete(E e) {
        getDao().delete(e);
    }

    @Override
    public E getById(Integer id) {
        return getDao().getById(id);
    }

    @Override
    public List<E> list() {
        return getDao().list();
    }
}
