package com.cesarlozza.service.impl;

import java.util.List;
import java.util.Optional;

import com.cesarlozza.model.Venta;
import com.cesarlozza.repo.IVentaRepo;
import com.cesarlozza.service.IVentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaServiceImpl implements IVentaService{

    @Autowired
    private IVentaRepo venta;

    @Override
    public Venta registrar(Venta obj) {
        obj.getDetalleVenta().forEach(det -> {
            det.setVenta(obj);
        });
        return venta.save(obj);
    }

    @Override
    public Venta modificar(Venta obj) {
        return venta.save(obj);
    }

    @Override
    public List<Venta> listar() {
        return venta.findAll();
    }

    @Override
    public Venta listarPorId(Integer id) {
        Optional<Venta> op = venta.findById(id);
        return  op.isPresent()?op.get():new Venta();
    }

    @Override
    public boolean eliminar(Integer id) {
        venta.deleteById(id);
        return true;
    }

}