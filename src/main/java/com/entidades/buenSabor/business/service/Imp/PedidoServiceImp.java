package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.*;
import jakarta.transaction.Transactional;
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
    @Autowired
    ClienteService clienteService;
    @Autowired
    EmpleadoService empleadoService;
    @Override
    @Transactional
    public Pedido create(Pedido pedido){
        Factura facturaSaved = facturaService.create(pedido.getFactura());
        Set<DetallePedido> detallesToSave = pedido.getDetallePedidos();
        Cliente cliente = pedido.getCliente();
        Empleado empleado = pedido.getEmpleado();

        pedido.setFactura(facturaSaved);
        for (DetallePedido detalle: detallesToSave) {
            pedido.getDetallePedidos().add(detallePedidoService.create(detalle));
        }

        cliente.getPedidos().add(pedido);
        empleado.getPedidos().add(pedido);
        empleadoService.create(empleado);
        clienteService.create(cliente);
        return pedido;
    }
}
