package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.SucursalDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaMapper.class })
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto>{

}
