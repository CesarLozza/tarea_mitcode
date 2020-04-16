package com.cesarlozza.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion del paciente") //swagger
@Entity
@Table(name = "persona") //nombre de la tabla en la BD
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // para ponerle un autoincremental
    private Integer idPersona;

    @Schema(description = "Nombre debe tener minimo 3 caracteres") //swagger
    @Size(min = 3, message = "El nombre debe tener mínimo 3 caracteres")
    @Column(name = "nombres", nullable = false, length = 70) //nombre de la columna en BD
    private String nombres;

    @Size(min = 3, message = "El apellido debe tener mínimo 3 caracteres")
    @Column(name = "apellidos", nullable = false, length = 70) //, unique = true
    private String apellidos;

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    

}