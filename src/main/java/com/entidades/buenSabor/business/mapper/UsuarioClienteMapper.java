package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.UsuarioCliente.UsuarioClienteDto;
import com.entidades.buenSabor.domain.dto.UsuarioCliente.UsuarioClientePostDto;
import com.entidades.buenSabor.domain.entities.UsuarioCliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioClienteMapper extends BaseMapper<UsuarioCliente, UsuarioClienteDto, UsuarioClientePostDto, UsuarioClientePostDto> {
}
