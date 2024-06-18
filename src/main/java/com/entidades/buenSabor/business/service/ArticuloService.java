package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Articulo;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ArticuloService extends BaseService<Articulo, Long> {
    @Named("getArticuloById")
    public Articulo getArticuloById(Long id);
    @Named("findOrNull")
    public Articulo findOrNull(Long id);
    public List<Articulo> getAllArticulosVenta();
}
