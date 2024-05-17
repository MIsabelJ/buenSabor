package com.entidades.buenSabor.domain.dto.Categoria;

import com.entidades.buenSabor.domain.dto.ArticuloDto.ArticuloDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;

import java.util.HashSet;
import java.util.Set;

public class CategoriaDto extends BaseDto {
    private String denominacion;
    private Set<SucursalDto> sucursales;
    private Set<ArticuloDto> articulos;
    private Set<CategoriaDto> subCategorias;
}
