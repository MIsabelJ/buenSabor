package com.entidades.buenSabor.domain.dto.Sucursal;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import io.swagger.v3.oas.annotations.media.Schema;
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
    private String nombre;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Horario de apertura en formato HH:mm:ss")
    private LocalTime horarioApertura;
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Horario de apertura en formato HH:mm:ss")
    //@Schema sirve para darle formato a LocalTime
    private LocalTime horarioCierre;
    private Boolean esCasaMatriz;
    private DomicilioDto domicilio;
}
