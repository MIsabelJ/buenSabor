package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;

public interface PedidoFacade extends BaseFacade<PedidoDto, PedidoPostDto, PedidoPostDto, Long> {
    public PedidoDto cancelPedido(Long id, boolean reponer);
}
