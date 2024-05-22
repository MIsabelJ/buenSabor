package com.entidades.buenSabor.domain.dto.ArticuloInsumo;

import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloPostDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoPostDto {
    protected String denominacion;
    protected Double precioVenta;
    protected Set<UUID> idImagenes; //En este caso partidular deberiamos recibir un List<Long> de ids y crear un metodo personalizado
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
    protected Long idUnidadMedida;
    private Long idCategoria;
}
