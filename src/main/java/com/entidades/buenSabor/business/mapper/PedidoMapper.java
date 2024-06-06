package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ClienteService;
import com.entidades.buenSabor.business.service.DomicilioService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClienteService.class, SucursalService.class, EmpleadoService.class, DomicilioService.class, FacturaMapper.class, DetallePedidoMapper.class})
public interface PedidoMapper extends BaseMapper<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto>{

    @Override
    @Mapping(target = "cliente", source = "idCliente", qualifiedByName = "getById")
    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    @Mapping(target = "empleado", source = "idEmpleado", qualifiedByName = "getById")
    @Mapping(target = "domicilio", source = "idDomicilio", qualifiedByName = "getById")
    public Pedido toEntityCreate(PedidoPostDto source);
}
