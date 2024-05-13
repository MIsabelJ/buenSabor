package com.entidades.buenSabor.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SucursalShortDto extends BaseDto {

    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private boolean esCasaMatriz;

}
