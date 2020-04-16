package com.cesarlozza.service;

import java.util.List;

// genericos
public interface ICRUD<T,V> {

    T registrar(T obj);
    T modificar(T obj);
    List<T> listar();
    T listarPorId(V id);
    boolean eliminar(V id);

}