package com.entidades.buenSabor.domain.dto.PromocionDetalle;

import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PromocionDetallePostDto {
    public int cantidad;
    public Long idArticulo;
}
