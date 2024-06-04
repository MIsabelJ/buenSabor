package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.DomicilioService;
import com.entidades.buenSabor.business.service.UsuarioClienteService;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClientePostDto;
import com.entidades.buenSabor.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioClienteService.class, DomicilioService.class, PedidoMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto, ClientePostDto, ClientePostDto>{

    @Mapping(target = "usuario", source = "idUsuario", qualifiedByName = "getById")
    @Mapping(target = "domicilios", source = "idDomicilios", qualifiedByName = "getById")
    public Cliente toEntityCreate(ClientePostDto source);
}
