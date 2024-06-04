package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {SucursalService.class, PedidoMapper.class})
public interface EmpleadoMapper extends BaseMapper<Empleado, EmpleadoDto, EmpleadoPostDto, EmpleadoPostDto>{

    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    public Empleado toEntityCreate(EmpleadoPostDto source);
}
