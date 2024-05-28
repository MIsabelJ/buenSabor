package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.business.service.UnidadMedidaService;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {UnidadMedidaService.class, CategoriaService.class, ImagenArticuloService.class})
public interface ArticuloInsumoMapper extends BaseMapper<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoPostDto, ArticuloInsumoPostDto>{
    ArticuloInsumoMapper INSTANCE = Mappers.getMapper(ArticuloInsumoMapper.class);
    @Mapping(target = "unidadMedida", source = "idUnidadMedida", qualifiedByName = "getById")
    @Mapping(target = "categoria", source = "idCategoria", qualifiedByName = "getById")
    //Este @Mapping en especial llama al metodo que carga las imagenes en la nube y las retorna listas para persistirlas en la base de datos
    @Mapping(target = "imagenes", source = "imagenes", qualifiedByName = "uploadImages")
    public ArticuloInsumo toEntityCreate(ArticuloInsumoPostDto source);
}
