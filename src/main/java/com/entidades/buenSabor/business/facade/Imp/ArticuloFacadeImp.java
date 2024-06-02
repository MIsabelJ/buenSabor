package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import org.springframework.stereotype.Service;

@Service
public class ArticuloFacadeImp extends BaseFacadeImp<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto, Long> implements ArticuloFacade {
    public ArticuloFacadeImp(BaseService<Articulo, Long> baseService, BaseMapper<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

}
