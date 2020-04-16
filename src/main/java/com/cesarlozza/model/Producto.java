package com.cesarlozza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

// JPQL
// FROM producto p(nombre de la entidad) WHERE p.idPaciente = 1 
@Entity //(name = "producto") //para colocarle un alias a la entidad 
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducto;

    @Size(min = 3, message = "El nombre del producto debe tener mínimo 3 caracteres")
    @Column(name = "nombre", nullable = false, length = 70)
    private String nombre;

    @Size(min = 3, message = "El nombre de la marca debe tener mínimo 3 caracteres")
    @Column(name = "marca", nullable = false, length = 70)
    private String marca;

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}