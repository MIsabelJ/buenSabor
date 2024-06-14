package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;

import java.util.List;
import java.util.Set;

public interface ArticuloFacade extends BaseFacade<ArticuloDto, ArticuloPostDto, ArticuloPostDto, Long> {

    public ArticuloDto instanceArticulo(Articulo articulo);

    public List<ArticuloDto> getAllArticulosVenta();
}
