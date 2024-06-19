package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.*;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoPostDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UnidadMedidaService.class, CategoriaService.class, ArticuloManufacturadoDetalleMapper.class, SucursalService.class})
public interface ArticuloManufacturadoMapper extends BaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto, ArticuloManufacturadoPostDto, ArticuloManufacturadoPostDto>{

    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    public ArticuloManufacturado toEntityCreate(ArticuloManufacturadoPostDto source);
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    @Mapping(target = "sucursal", source = "idSucursal", qualifiedByName = "getById")
    public  ArticuloManufacturado toUpdate(@MappingTarget ArticuloManufacturado entity, ArticuloManufacturadoPostDto source);
}
