package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.Sucursalfacade;
import com.entidades.buenSabor.business.mapper.*;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SucursalFacadeImp extends BaseFacadeImp<Sucursal, SucursalDto, SucursalPostDto, SucursalUpdateDto, Long> implements Sucursalfacade {
    public SucursalFacadeImp(BaseService<Sucursal, Long> baseService, BaseMapper<Sucursal, SucursalDto, SucursalPostDto, SucursalUpdateDto> baseMapper) {
        super(baseService, baseMapper);
    }
    @Autowired
    SucursalMapper sucursalMapper;
    @Autowired
    SucursalService sucursalService;
    @Autowired
    CategoriaMapper categoriaMapper;
    @Autowired
    PromocionMapper promocionMapper;
    @Autowired
    PedidoMapper pedidoMapper;
    @Autowired
    ArticuloMapper articuloMapper;
    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;

    public List<CategoriaDto> findCategoriaBySucursalId(Long idSucursal){
        return categoriaMapper.toDTOsList(sucursalService.getCategoriasBySucursalId(idSucursal));
    }

    @Override
    public List<PromocionDto> findPromocionesBySucursalId(Long idSucursal){
        return promocionMapper.toDTOsList(sucursalService.getPromocionesBySucursalId(idSucursal));
    }
    @Override
    public List<PedidoDto> findPedidosBySucursalId(Long idSucursal){
        return pedidoMapper.toDTOsList(sucursalService.getPedidosBySucursalId(idSucursal));
    }
    @Override
    public List<ArticuloDto> findArticulosBySucursalId(Long idSucursal){
        return articuloMapper.toDTOsList(sucursalService.getArticulosBySucursalId(idSucursal));
    }
    @Override
    public List<ArticuloInsumoDto> findArticuloInsumosBySucursalId(Long idSucursal){
        return articuloInsumoMapper.toDTOsList(sucursalService.getArticuloInsumosBySucursalId(idSucursal));
    }
    @Override
    public List<ArticuloManufacturadoDto> findArticuloManufacturadosBySucursalId(Long idSucursal){
        return articuloManufacturadoMapper.toDTOsList(sucursalService.getArticuloManufacturadosBySucursalId(idSucursal));
    }

}
