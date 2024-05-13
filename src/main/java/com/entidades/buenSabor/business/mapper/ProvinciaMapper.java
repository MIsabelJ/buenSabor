package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaDto;
import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaPostDto;
import com.entidades.buenSabor.domain.entities.Provincia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProvinciaMapper extends BaseMapper<Provincia,ProvinciaDto, ProvinciaPostDto, ProvinciaPostDto>{

}
