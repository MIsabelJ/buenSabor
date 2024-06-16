package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.UsuarioEmpleado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEmpleadoMapper extends BaseMapper<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoPostDto, UsuarioEmpleadoPostDto>{
}
