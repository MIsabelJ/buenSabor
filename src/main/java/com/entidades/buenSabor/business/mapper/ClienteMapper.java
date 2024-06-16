package com.entidades.buenSabor.business.mapper;


import com.entidades.buenSabor.business.service.UsuarioClienteService;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClientePostDto;
import com.entidades.buenSabor.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UsuarioClienteMapper.class,DomicilioMapper.class, PedidoMapper.class})
public interface ClienteMapper extends BaseMapper<Cliente, ClienteDto, ClientePostDto, ClientePostDto>{

    public Cliente toEntityCreate(ClientePostDto source);
}
