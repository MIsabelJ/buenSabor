package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.UnidadMedidaFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaPostDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaFacadeImp extends BaseFacadeImp<UnidadMedida, UnidadMedidaDto, UnidadMedidaPostDto, UnidadMedidaPostDto, Long> implements UnidadMedidaFacade {
    public UnidadMedidaFacadeImp(BaseService<UnidadMedida, Long> baseService, BaseMapper<UnidadMedida, UnidadMedidaDto, UnidadMedidaPostDto, UnidadMedidaPostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
