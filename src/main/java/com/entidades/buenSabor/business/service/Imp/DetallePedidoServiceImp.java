package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.DetallePedidoService;
import com.entidades.buenSabor.domain.entities.*;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoServiceImp extends BaseServiceImp<DetallePedido, Long> implements DetallePedidoService {
    @Override
    public DetallePedido descuentoDeStock(DetallePedido detalle) {
        Articulo articulo = detalle.getArticulo();

        // Si el artículo es un ArticuloInsumo
        if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
            int nuevoStock = articuloInsumo.getStockActual() - detalle.getCantidad();
            if (nuevoStock > articuloInsumo.getStockMinimo()){
                articuloInsumo.setStockActual(nuevoStock);
                detalle.setArticulo(articuloInsumo);
            }else if (nuevoStock < articuloInsumo.getStockMinimo() && nuevoStock > 0){
                articuloInsumo.setStockActual(nuevoStock);
                articuloInsumo.setEliminado(true);
                detalle.setArticulo(articuloInsumo);
            }else if (nuevoStock < 0){
                return null;
            }

            // Si el artículo es un ArticuloManufacturado
        } else if (articulo instanceof ArticuloManufacturado) {
            ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
            for (ArticuloManufacturadoDetalle detalleManufacturado : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                ArticuloInsumo articuloInsumo = detalleManufacturado.getArticuloInsumo();
                int nuevoStock = articuloInsumo.getStockActual();
                for (int i = 0; i < detalle.getCantidad(); i++){
                    nuevoStock = nuevoStock - detalleManufacturado.getCantidad();
                }
                if(nuevoStock > articuloInsumo.getStockMinimo()){
                    articuloInsumo.setStockActual(nuevoStock);
                    detalleManufacturado.setArticuloInsumo(articuloInsumo);
                }else if (nuevoStock < articuloInsumo.getStockMinimo() && nuevoStock > 0){
                    articuloInsumo.setStockActual(nuevoStock);
                    articuloInsumo.setEliminado(true);
                    detalleManufacturado.setArticuloInsumo(articuloInsumo);
                } else if (nuevoStock < 0) {
                    return null;
                }

            }
            detalle.setArticulo(articuloManufacturado);
        }

        return detalle;
    }
}
