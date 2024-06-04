package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.DetallePedidoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoDto;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoPostDto;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import org.springframework.stereotype.Service;

@Service
public class DetallePedidoFacadeImp extends BaseFacadeImp<DetallePedido, DetallePedidoDto, DetallePedidoPostDto, DetallePedidoPostDto, Long> implements DetallePedidoFacade {
    public DetallePedidoFacadeImp(BaseService<DetallePedido, Long> baseService, BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoPostDto, DetallePedidoPostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
