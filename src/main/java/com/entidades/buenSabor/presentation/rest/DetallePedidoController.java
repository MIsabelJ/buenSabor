package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.DetallePedidoFacadeImp;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoDto;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoPostDto;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

@RestController
@RequestMapping("/detalle-pedido")
@CrossOrigin("*")
public class DetallePedidoController extends BaseControllerImp<DetallePedido, DetallePedidoDto, DetallePedidoPostDto, DetallePedidoPostDto, Long, DetallePedidoFacadeImp> {
    public DetallePedidoController(DetallePedidoFacadeImp facade) {
        super(facade);
    }
}
