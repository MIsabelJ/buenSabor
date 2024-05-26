package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.CategoriaFacade;
import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
