package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring", uses = {UnidadMedidaService.class})
public abstract interface ArticuloMapper extends BaseMapper<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto>{

    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    public Articulo toEntityCreate(ArticuloPostDto source);
}