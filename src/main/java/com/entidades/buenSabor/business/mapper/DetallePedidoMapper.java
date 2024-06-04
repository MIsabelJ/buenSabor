package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoDto;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoPostDto;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.io.Serializable;

@Mapper(componentModel = "spring", uses = {ArticuloService.class})
public interface DetallePedidoMapper extends BaseMapper<DetallePedido, DetallePedidoDto, DetallePedidoPostDto, DetallePedidoPostDto> {

    @Mapping(target = "articulo", source = "idArticulo", qualifiedByName = "getById")
    public DetallePedido toEntityCreate(DetallePedidoPostDto source);
}
