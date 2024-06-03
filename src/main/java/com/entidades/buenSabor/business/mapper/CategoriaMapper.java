package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

/*@Mapper(componentModel = "spring", uses = {SucursalService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto, CategoriaPostDto, CategoriaPostDto>{
    @Mapping(target = "sucursales", source = "idSucursales", qualifiedByName = "getSetById")
    @Mapping(target = "sucursales", source = "idInsumos", qualifiedByName = "getSetById")
    @Mapping(target = "articulos", source = "idArticulosManufacturados", qualifiedByName = "getSetById")
    @Mapping(target = "sucursales", source = "idSubCategorias", qualifiedByName = "getSetById")
    public Categoria toEntityCreate(CategoriaPostDto source);

}
*/
@Mapper(componentModel = "spring", uses = {SucursalService.class, CategoriaService.class})
public interface CategoriaMapper extends BaseMapper<Categoria, CategoriaDto,CategoriaPostDto, CategoriaPostDto> {

    // Este método define la transformación de un CategoriaCreateDto a una entidad Categoria.
    @Mapping(target = "sucursales", source = "idSucursales",qualifiedByName = "getById")
    @Mapping(target = "subCategorias", source = "idSubCategorias",qualifiedByName = "getById")
    public Categoria toEntityCreate(CategoriaPostDto source);

    @Mapping(target = "sucursales", source = "idSucursales",qualifiedByName = "getById")
    @Mapping(target = "subCategorias", source = "idSubCategorias",qualifiedByName = "getById")
    public Categoria toUpdate(@MappingTarget Categoria entity, CategoriaPostDto source);

}