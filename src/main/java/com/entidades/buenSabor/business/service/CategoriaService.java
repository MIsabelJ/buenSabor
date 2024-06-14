package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Categoria;

import java.util.List;

public interface CategoriaService extends BaseService<Categoria, Long> {
    public Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate);

    public List<ArticuloInsumo> getInsumoByCategoriaId(Long idCategoria);
    public List<ArticuloManufacturado> getManufacturadoByCategoriaId(Long idCategoria);
    public List<Categoria> getAllCategoriasVenta();
    public List<Articulo> getArticulosByCategoriaId(Long idCategoria);
}
