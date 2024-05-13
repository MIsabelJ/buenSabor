package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {DomicilioMapper.class, EmpresaMapper.class })
public interface SucursalMapper extends BaseMapper<Sucursal, SucursalDto, SucursalPostDto, SucursalUpdateDto>{

}
