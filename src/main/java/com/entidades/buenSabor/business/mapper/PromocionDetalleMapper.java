package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetallePostDto;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ArticuloService.class})
public interface PromocionDetalleMapper extends BaseMapper<PromocionDetalle, PromocionDetalleDto, PromocionDetallePostDto, PromocionDetallePostDto>{
    @Mapping(target = "articulo", source = "idArticulo", qualifiedByName = "getById")
    public PromocionDetalle toEntityCreate(PromocionDetallePostDto source);
}
