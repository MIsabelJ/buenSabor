package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ImagenPromocionService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionPostDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PromocionDetalleMapper.class, SucursalService.class})
public interface PromocionMapper extends BaseMapper<Promocion, PromocionDto, PromocionPostDto, PromocionPostDto>{
    @Mapping(target = "sucursales", source = "idSucursales", qualifiedByName = "getById")
    public Promocion toEntityCreate (PromocionPostDto source);

    @Mapping(target = "sucursales", source = "idSucursales", qualifiedByName = "getById")
    public Promocion toUpdate (@MappingTarget Promocion entity, PromocionPostDto source);
}
