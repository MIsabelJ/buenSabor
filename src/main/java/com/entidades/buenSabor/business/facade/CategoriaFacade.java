package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, CategoriaPostDto, CategoriaPostDto, Long> {
    public CategoriaDto addSubCategoria(Long idCategoria, CategoriaPostDto subCategoria);
}
