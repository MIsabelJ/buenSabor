package com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle;

import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoDetalleDto extends BaseDto {
    private Integer cantidad;
    private ArticuloInsumoDto articuloInsumo;
}
