package com.entidades.buenSabor.domain.dto.ArticuloInsumo;

import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalShortDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.UnidadMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoDto extends ArticuloDto {
    private Integer stockActual;
    private Integer stockMaximo;
    private Integer stockMinimo;
    private Boolean esParaElaborar;
    private CategoriaDto categoria;
    private SucursalShortDto sucursal;
}
