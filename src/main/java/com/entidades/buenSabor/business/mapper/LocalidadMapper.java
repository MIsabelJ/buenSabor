package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ProvinciaService;
import com.entidades.buenSabor.domain.dto.Localidad.LocalidadDto;
import com.entidades.buenSabor.domain.dto.Localidad.LocalidadPostDto;
import com.entidades.buenSabor.domain.entities.Localidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProvinciaService.class})
public interface LocalidadMapper extends BaseMapper<Localidad, LocalidadDto, LocalidadPostDto, LocalidadPostDto> {

    @Mapping(target = "provincia", source = "idProvincia",qualifiedByName = "getById")
    Localidad toEntityCreate(LocalidadPostDto source);
}
