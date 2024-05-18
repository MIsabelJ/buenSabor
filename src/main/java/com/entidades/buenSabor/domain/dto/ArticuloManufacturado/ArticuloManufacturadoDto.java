package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;

import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoDto extends BaseDto {
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private Set<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalles;
}
