package com.entidades.buenSabor.business.mapper;


import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoDetalleService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetallePostDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticuloInsumoService.class})
public interface ArticuloManufacturadoDetalleMapper extends BaseMapper<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetallePostDto, ArticuloManufacturadoDetallePostDto> {
    @Mapping(target = "articuloInsumo", source = "idArticuloInsumo", qualifiedByName = "getById")
    public ArticuloManufacturadoDetalle toEntityCreate(ArticuloManufacturadoDetallePostDto source);
}
