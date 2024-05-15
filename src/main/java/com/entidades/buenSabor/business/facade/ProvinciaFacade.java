package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaDto;
import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaPostDto;

import java.util.List;

public interface ProvinciaFacade extends BaseFacade<ProvinciaDto, ProvinciaPostDto, ProvinciaPostDto, Long> {
    List<ProvinciaDto> findByPaisId(Long id);
}
