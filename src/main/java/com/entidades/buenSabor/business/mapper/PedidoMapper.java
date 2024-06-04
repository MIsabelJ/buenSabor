package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto>{

}
