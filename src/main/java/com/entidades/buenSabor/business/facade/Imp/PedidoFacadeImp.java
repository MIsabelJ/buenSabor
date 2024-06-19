package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PedidoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.Imp.PedidoServiceImp;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoFacadeImp extends BaseFacadeImp<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto, Long> implements PedidoFacade {


    public PedidoFacadeImp(BaseService<Pedido, Long> baseService, BaseMapper<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    private PedidoServiceImp pedidoServiceImp;

    @Override
    public PedidoDto cancelPedido(Long id, boolean reponer){
        return baseMapper.toDTO(pedidoServiceImp.cancelPedido(id, reponer));
    }
}
