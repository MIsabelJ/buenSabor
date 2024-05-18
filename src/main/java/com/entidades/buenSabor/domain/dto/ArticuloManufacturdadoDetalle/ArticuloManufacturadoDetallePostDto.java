package com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle;

import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloManufacturadoDetallePostDto {
    private Integer cantidad;
    private ArticuloInsumoDto articuloInsumo;
}
