package com.entidades.buenSabor.domain.dto.Domicilio;

import com.entidades.buenSabor.domain.dto.Localidad.LocalidadDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DomicilioPostDto {
    private String calle;
    private Integer numero;
    private Integer cp;
    private Integer piso;
    private Integer nroDpto;
    private Long idLocalidad;
}
