package com.entidades.buenSabor.domain.dto.ArticuloInsumo;

import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoDto extends BaseDto {
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Boolean esParaElaborar;
}
