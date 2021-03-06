package com.aimprosoft.department.dao;


import com.aimprosoft.department.entity.BusinessEntity;
import java.util.List;

/**
 * Created by merovingien on 3/11/14.
 */
public interface Dao<E extends BusinessEntity> {
    Integer add(E e);
    void update(E e);
    void delete(E e);
    E getById(Integer id);
    List<E> list();
//    List<E> listByCriteria(Criterion criterion);
}
