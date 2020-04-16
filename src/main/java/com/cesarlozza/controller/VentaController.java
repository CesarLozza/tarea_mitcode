package com.cesarlozza.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.cesarlozza.exception.ModeloNotFoundException;
import com.cesarlozza.model.Venta;
import com.cesarlozza.service.IVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/ventas")
public class VentaController{

    @Autowired
    private IVentaService service;

    @GetMapping
    public ResponseEntity<List<Venta>> listar(){
        List<Venta> lista = service.listar();
        return new ResponseEntity<List<Venta>>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer id){
        Venta p = service.listarPorId(id);
        if(p.getIdVenta() == null) throw new ModeloNotFoundException("Venta no encontrada: " + id);
        return new ResponseEntity<Venta>(p, HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona persona){
        Persona p = service.registrar(persona);
        return new ResponseEntity<Persona>(p,HttpStatus.CREATED);
    }
    */

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta){
        Venta p = service.registrar(venta);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdVenta()).toUri();
        return ResponseEntity.created(location).build();
    }

}