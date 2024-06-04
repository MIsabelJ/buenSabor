package com.entidades.buenSabor.domain.dto.DetallePedido;

import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DetallePedidoDto extends BaseDto {
    private Integer cantidad;
    private Double subTotal;

    private ArticuloDto articulo;
}
