package com.aimprosoft.department.dao;


import com.aimprosoft.department.entity.BusinessEntity;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by merovingien on 3/11/14.
 */
public interface Dao<E extends BusinessEntity, I extends Serializable> {
    I add(E e);
    void update(E e);
    void delete(E e);
    E getByCriteria(Criterion criterion);
    List<E> list();
//    List<E> listByCriteria(Criterion criterion);
//    List<E> listByCriteria(E e);
}
