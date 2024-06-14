package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoMapper;
import com.entidades.buenSabor.business.mapper.ArticuloMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaFacadeImp extends BaseFacadeImp<Categoria, CategoriaDto, CategoriaPostDto, CategoriaPostDto, Long> implements CategoriaFacade {
    public CategoriaFacadeImp(BaseService<Categoria, Long> baseService, BaseMapper<Categoria, CategoriaDto, CategoriaPostDto, CategoriaPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    ArticuloInsumoMapper insumoMapper;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    ArticuloMapper articuloMapper;
    @Autowired
    ArticuloService articuloService;
    @Override
    public CategoriaDto addSubCategoria(Long idCategoria, CategoriaPostDto subCategoria) {
        Categoria subCategoriaToCreate = baseMapper.toEntityCreate(subCategoria);
        return baseMapper.toDTO(categoriaService.addSubCategoria(idCategoria, subCategoriaToCreate));
    }

    @Override
    public List<ArticuloInsumoDto> getInsumoByCategoriaId(Long idCategoria){
        return insumoMapper.toDTOsList(categoriaService.getInsumoByCategoriaId(idCategoria));
    }

    public List<ArticuloManufacturadoDto> getManufacturadoByCategoriaId(Long idCategoria){
        return articuloManufacturadoMapper.toDTOsList(categoriaService.getManufacturadoByCategoriaId(idCategoria));
    }

    public List<ArticuloDto> getArticulosByCategoriaId(Long idCategoria){
        List<Articulo> articulos = categoriaService.getArticulosByCategoriaId(idCategoria);

        return articulos.stream()
                .map(articulo -> {
                    if (articulo instanceof ArticuloInsumo) {
                        return insumoMapper.toDTO((ArticuloInsumo) articulo);
                    } else if (articulo instanceof ArticuloManufacturado) {
                        return articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                    } else {
                        throw new RuntimeException("Tipo de artículo no soportado: " + articulo.getClass().getName());
                    }
                })
                .collect(Collectors.toList());
    }

    public List<CategoriaDto> getCategoriasVenta(){
        return baseMapper.toDTOsList(categoriaService.getAllCategoriasVenta());
    }

}
