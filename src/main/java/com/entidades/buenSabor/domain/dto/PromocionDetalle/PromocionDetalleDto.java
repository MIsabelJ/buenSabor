package com.entidades.buenSabor.domain.dto.PromocionDetalle;

import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PromocionDetalleDto extends BaseDto {
    private int cantidad;
    private ArticuloDto articulo;
}
