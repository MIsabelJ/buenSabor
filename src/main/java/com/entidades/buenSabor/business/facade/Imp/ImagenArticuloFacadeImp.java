package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.ImagenArticuloFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloPostDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.stereotype.Service;

@Service
public class ImagenArticuloFacadeImp extends BaseFacadeImp<ImagenArticulo, ImagenArticuloDto, ImagenArticuloPostDto, ImagenArticuloPostDto, Long> implements ImagenArticuloFacade {
    public ImagenArticuloFacadeImp(BaseService<ImagenArticulo, Long> baseService, BaseMapper<ImagenArticulo, ImagenArticuloDto, ImagenArticuloPostDto, ImagenArticuloPostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
