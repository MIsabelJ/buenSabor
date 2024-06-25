package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.*;

import java.util.List;
import java.util.Set;

public interface SucursalService  extends BaseService<Sucursal, Long> {
    public List<Categoria> getCategoriasBySucursalId(Long idSucursal);
    public List<Promocion> getPromocionesBySucursalId(Long idSucursal);
    public List<Pedido> getPedidosBySucursalId(Long idSucursal);
    public List<Articulo> getArticulosBySucursalId(Long idSucursal);
    public List<ArticuloInsumo> getArticuloInsumosBySucursalId(Long idSucursal);
    public List<ArticuloManufacturado> getArticuloManufacturadosBySucursalId(Long idSucursal);

}

