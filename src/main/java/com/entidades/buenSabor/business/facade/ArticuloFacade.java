package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;

public interface ArticuloFacade extends BaseFacade<ArticuloDto, ArticuloPostDto, ArticuloPostDto, Long> {
}
