package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.DetallePedido;

public interface DetallePedidoService extends BaseService<DetallePedido, Long> {
    public DetallePedido descuentoDeStock (DetallePedido detalle);
    public Articulo descuentoStockArticulo (Articulo articulo, int cantidad);
    public DetallePedido reponerStock (DetallePedido detalle);
    public Articulo reponerStockArticulo (Articulo articulo, int cantidad);
}
