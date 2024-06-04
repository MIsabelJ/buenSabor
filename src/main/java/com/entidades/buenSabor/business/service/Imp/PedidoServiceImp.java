package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PedidoServiceImp extends BaseServiceImp<Pedido, Long> implements PedidoService {
    @Autowired
    DomicilioService domicilioService;
    @Autowired
    FacturaService facturaService;
    @Autowired
    DetallePedidoService detallePedidoService;
    @Override
    public Pedido create(Pedido pedido){
        Domicilio domicilioSaved = domicilioService.create(pedido.getDomicilio());
        Factura facturaSaved = facturaService.create(pedido.getFactura());
        Set<DetallePedido> detallesToSave = pedido.getDetallePedidos();
        pedido.setDomicilio(domicilioSaved);
        pedido.setFactura(facturaSaved);
        for (DetallePedido detalle: detallesToSave) {
            pedido.getDetallePedidos().add(detallePedidoService.create(detalle));
        }
        pedido.getCliente().getPedidos().add(pedido);
        pedido.getEmpleado().getPedidos().add(pedido);
        return pedido;
    }
}
