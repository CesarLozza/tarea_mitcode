package com.cesarlozza.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.cesarlozza.exception.ModeloNotFoundException;
import com.cesarlozza.model.Producto;
import com.cesarlozza.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService service;

    @GetMapping
    public ResponseEntity<List<Producto>> listar(){
        List<Producto> lista = service.listar();
        return new ResponseEntity<List<Producto>>(lista,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id){
        Producto p = service.listarPorId(id);
        if(p.getIdProducto() == null) throw new ModeloNotFoundException("Producto no encontrado: " + id);
        return new ResponseEntity<Producto>(p, HttpStatus.OK);
    }
    /*
    @PostMapping
    public ResponseEntity<Producto> registrar(@Valid @RequestBody Persona producto){
        Producto p = service.registrar(producto);
        return new ResponseEntity<Producto>(p,HttpStatus.CREATED);
    }
    */

    @PostMapping
    public ResponseEntity<Object> registrar(@Valid @RequestBody Producto producto){
        Producto p = service.registrar(producto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdProducto()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto){
        Producto p = service.modificar(producto);
        return new ResponseEntity<Producto>(p,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id){
        Producto p = service.listarPorId(id);
        if(p.getIdProducto() == null) throw new ModeloNotFoundException("Producto no encontrado: " + id);
        service.eliminar(id);
        return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
    }

}