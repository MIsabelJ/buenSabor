package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;

import java.util.List;

public interface CategoriaFacade extends BaseFacade<CategoriaDto, CategoriaPostDto, CategoriaPostDto, Long> {
    public CategoriaDto addSubCategoria(Long idCategoria, CategoriaPostDto subCategoria);
    public List<ArticuloInsumoDto> getInsumoByCategoriaId(Long idCategoria);
    public List<ArticuloManufacturadoDto> getManufacturadoByCategoriaId(Long idCategoria);
    public List<ArticuloDto> getArticulosByCategoriaId(Long idCategoria);
}
