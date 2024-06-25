package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;
import com.entidades.buenSabor.domain.entities.*;

import java.util.List;
import java.util.Set;

public interface Sucursalfacade extends BaseFacade<SucursalDto, SucursalPostDto, SucursalUpdateDto, Long> {
    public List<CategoriaDto> findCategoriaBySucursalId(Long idSucursal);
    public List<PromocionDto> findPromocionesBySucursalId(Long idSucursal);
    public List<PedidoDto> findPedidosBySucursalId(Long idSucursal);
    public List<ArticuloDto> findArticulosBySucursalId(Long idSucursal);
    public List<ArticuloInsumoDto> findArticuloInsumosBySucursalId(Long idSucursal);
    public List<ArticuloManufacturadoDto> findArticuloManufacturadosBySucursalId(Long idSucursal);

}
