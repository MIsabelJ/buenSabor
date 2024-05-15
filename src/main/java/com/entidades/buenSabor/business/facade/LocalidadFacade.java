package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Localidad.LocalidadDto;
import com.entidades.buenSabor.domain.dto.Localidad.LocalidadPostDto;

import java.util.List;

public interface LocalidadFacade extends BaseFacade<LocalidadDto, LocalidadPostDto, LocalidadPostDto, Long> {

    List<LocalidadDto> findByProvinciaId(Long id);
}
