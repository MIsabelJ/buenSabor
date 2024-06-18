package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    @Autowired
    PedidoRepository pedidoRepository;
    @Override
    @Transactional
    public Pedido create(Pedido pedido){
        Set<DetallePedido> detallesToSave = pedido.getDetallePedidos();
        Set<DetallePedido> detallesActualizados = new HashSet<>();

        // Aplica descuento de stock a cada detalle del pedido
        try{
            for (DetallePedido detalle : detallesToSave) {
                DetallePedido actualizado = detallePedidoService.descuentoDeStock(detalle);
                if (actualizado != null) {
                    detallesActualizados.add(actualizado);
                } else {
                    throw new Exception("EL PEDIDO EXCEDE EL STOCK");
                }
            }
        }catch (Exception e){
            pedido.setEstado(Estado.RECHAZADO);
            return super.create(pedido);
        }

        pedido.setDetallePedidos(detallesActualizados);

        pedido.getEmpleado().getPedidos().add(pedido);
        pedido.getCliente().getPedidos().add(pedido);

        return super.create(pedido);
    }

    public Pedido cancelPedido(Long id, boolean reponer){
        Pedido pedidoOld = super.getById(id);

        if (reponer == true){
            Set<DetallePedido> detallesAct = new HashSet<>();
            for (DetallePedido detalle : pedidoOld.getDetallePedidos()){
                detallesAct.add(detallePedidoService.reponerStock(detalle));
            }
            pedidoOld.setDetallePedidos(detallesAct);
        }

        pedidoOld.setEstado(Estado.CANCELADO);
        return super.update(pedidoOld, id);

    }
}
