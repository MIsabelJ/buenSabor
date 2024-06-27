package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.DetallePedidoService;
import com.entidades.buenSabor.domain.entities.*;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class DetallePedidoServiceImp extends BaseServiceImp<DetallePedido, Long> implements DetallePedidoService {
    @Override
    public DetallePedido descuentoDeStock(DetallePedido detalle) {
        if (detalle.getArticulo() != null) {
            Articulo articulo = detalle.getArticulo();
            Articulo articuloActualizado = descuentoStockArticulo(articulo, detalle.getCantidad());
            if (articuloActualizado == null) {
                return null; // Retornar null si no hay suficiente stock
            }
            detalle.setArticulo(articuloActualizado);
        }

        if (detalle.getPromocion() != null) {
            Set<PromocionDetalle> promocionDetalles = detalle.getPromocion().getPromocionDetalles();
            Set<PromocionDetalle> promocionDetallesActualizados = new HashSet<>();
            for (PromocionDetalle promocionDetalle : promocionDetalles) {
                Articulo articulo = promocionDetalle.getArticulo();
                Articulo articuloActualizado = descuentoStockArticulo(articulo, promocionDetalle.getCantidad() * detalle.getCantidad());
                if (articuloActualizado == null) {
                    return null; // Retornar null si no hay suficiente stock
                }
                promocionDetalle.setArticulo(articuloActualizado);
                promocionDetallesActualizados.add(promocionDetalle);
            }
            detalle.getPromocion().setPromocionDetalles(promocionDetallesActualizados);
        }

        return detalle;
    }

    public Articulo descuentoStockArticulo(Articulo articulo, int cantidad) {
        if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
            int nuevoStock = articuloInsumo.getStockActual() - cantidad;
            if (nuevoStock >= 0) {
                articuloInsumo.setStockActual(nuevoStock);
                return articuloInsumo;
            } else {
                return null; // Retornar null si no hay suficiente stock
            }
        } else if (articulo instanceof ArticuloManufacturado) {
            ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
            for (ArticuloManufacturadoDetalle detalleManufacturado : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                ArticuloInsumo articuloInsumo = detalleManufacturado.getArticuloInsumo();
                int nuevoStock = articuloInsumo.getStockActual() - detalleManufacturado.getCantidad() * cantidad;
                if (nuevoStock >= 0) {
                    articuloInsumo.setStockActual(nuevoStock);
                } else {
                    return null; // Retornar null si no hay suficiente stock
                }
            }
            return articuloManufacturado;
        }
        return null;
    }

    @Override
    public DetallePedido reponerStock (DetallePedido detalle){
        if (detalle.getArticulo() != null) {
            Articulo articulo = detalle.getArticulo();
            Articulo articuloActualizado = reponerStockArticulo(articulo, detalle.getCantidad());
            detalle.setArticulo(articuloActualizado);
        }
        if (detalle.getPromocion() != null){
            Set<PromocionDetalle> promocionDetalles = detalle.getPromocion().getPromocionDetalles();
            Set<PromocionDetalle> promocionDetallesActualizados = new HashSet<>();
            for (PromocionDetalle promocionDetalle : promocionDetalles) {
                Articulo articulo = promocionDetalle.getArticulo();
                articulo = reponerStockArticulo(articulo, promocionDetalle.getCantidad());
                promocionDetalle.setArticulo(articulo);
                promocionDetallesActualizados.add(promocionDetalle);
            }
            detalle.getPromocion().setPromocionDetalles(promocionDetallesActualizados);
        }

        return detalle;
    }

    @Override
    public Articulo reponerStockArticulo (Articulo articulo, int cantidad){
        if (articulo instanceof ArticuloInsumo) {
            ArticuloInsumo articuloInsumo = (ArticuloInsumo) articulo;
            int nuevoStock = articuloInsumo.getStockActual() + cantidad;
            articuloInsumo.setStockActual(nuevoStock);
            return articuloInsumo;

        } else if (articulo instanceof ArticuloManufacturado) {
            ArticuloManufacturado articuloManufacturado = (ArticuloManufacturado) articulo;
            Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();
            for (ArticuloManufacturadoDetalle detalleManufacturado : articuloManufacturado.getArticuloManufacturadoDetalles()) {
                ArticuloInsumo articuloInsumo = detalleManufacturado.getArticuloInsumo();
                int nuevoStock = articuloInsumo.getStockActual();
                for (int i = 0; i < cantidad; i++){
                    nuevoStock = nuevoStock + detalleManufacturado.getCantidad();
                }
                articuloInsumo.setStockActual(nuevoStock);
                detalleManufacturado.setArticuloInsumo(articuloInsumo);
                detalles.add(detalleManufacturado);
            }
            articuloManufacturado.setArticuloManufacturadoDetalles(detalles);
            return articuloManufacturado;
        }
        return null;
    }
}
