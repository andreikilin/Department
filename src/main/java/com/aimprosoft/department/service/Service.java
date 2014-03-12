package com.aimprosoft.department.service;

import com.aimprosoft.department.entity.BusinessEntity;

import java.util.List;

/**
 * Created by merovingien on 3/12/14.
 */
public interface Service<E extends BusinessEntity> {
    Integer add(E e);
    void update(E e);
    void delete(E e);
    E getById(Integer id);
    List<E> list();
}
