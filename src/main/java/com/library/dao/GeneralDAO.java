package com.library.dao;

import java.util.List;

public interface GeneralDAO<E> {

    public void addElement(E element);
    public void updateElement(E oldElement,E newElement);
    public List<E> getAllElements();
    public void deleteElement(E element);

}