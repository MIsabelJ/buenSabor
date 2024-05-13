package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.DomicilioFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioPostDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadeImp extends BaseFacadeImp<Domicilio, DomicilioDto, DomicilioPostDto, DomicilioPostDto, Long> implements DomicilioFacade {
    public DomicilioFacadeImp(BaseService<Domicilio, Long> baseService, BaseMapper<Domicilio, DomicilioDto, DomicilioPostDto, DomicilioPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

}
