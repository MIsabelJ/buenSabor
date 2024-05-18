package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaPostDto;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnidadMedidaMapper extends BaseMapper<UnidadMedida, UnidadMedidaDto, UnidadMedidaPostDto, UnidadMedidaPostDto>{
}
