package com.entidades.buenSabor.domain.dto.Articulo;

import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloPostDto {
    private String denominacion;
    private Double precioVenta;

    private Set<String> idImagenes;
    private Long idUnidadMedida;
}
