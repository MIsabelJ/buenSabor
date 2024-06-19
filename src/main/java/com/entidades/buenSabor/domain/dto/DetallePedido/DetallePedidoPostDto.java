package com.entidades.buenSabor.domain.dto.DetallePedido;

import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetallePedidoPostDto {
    private Integer cantidad;
    private Double subTotal;

    private Long idArticulo;
    private Long idPromocion;
}
