package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.PaisService;
import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaDto;
import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaPostDto;
import com.entidades.buenSabor.domain.entities.Provincia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PaisService.class})
public interface ProvinciaMapper extends BaseMapper<Provincia,ProvinciaDto, ProvinciaPostDto, ProvinciaPostDto>{

    @Mapping(target = "pais", source = "idPais",qualifiedByName = "getById")
    Provincia toEntityCreate(ProvinciaPostDto source);

}
