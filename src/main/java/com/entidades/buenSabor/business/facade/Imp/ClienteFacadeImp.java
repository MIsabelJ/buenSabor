package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.ClienteFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClientePostDto;
import com.entidades.buenSabor.domain.entities.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteFacadeImp extends BaseFacadeImp<Cliente, ClienteDto, ClientePostDto, ClientePostDto, Long> implements ClienteFacade {
    public ClienteFacadeImp(BaseService<Cliente, Long> baseService, BaseMapper<Cliente, ClienteDto, ClientePostDto, ClientePostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
