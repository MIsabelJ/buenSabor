package com.entidades.buenSabor.domain.dto.Categoria;

import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoriaPostDto {
    private String denominacion;
    private Set<SucursalDto> sucursales;
    private Set<ArticuloInsumoDto> insumos;
    private Set<ArticuloManufacturadoDto> articulosManufacturados;
    private Set<CategoriaDto> subCategorias;
}
