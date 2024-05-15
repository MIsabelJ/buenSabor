package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PaisFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Pais.PaisDto;
import com.entidades.buenSabor.domain.dto.Pais.PaisPostDto;
import com.entidades.buenSabor.domain.entities.Pais;
import org.springframework.stereotype.Service;


@Service
public class PaisFacadeImp extends BaseFacadeImp<Pais, PaisDto, PaisPostDto, PaisPostDto, Long> implements PaisFacade {
    public PaisFacadeImp(BaseService<Pais, Long> baseService, BaseMapper<Pais, PaisDto, PaisPostDto, PaisPostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
