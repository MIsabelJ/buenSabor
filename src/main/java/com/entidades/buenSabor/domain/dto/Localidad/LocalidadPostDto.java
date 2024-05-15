package com.entidades.buenSabor.domain.dto.Localidad;

import com.entidades.buenSabor.domain.dto.Provincia.ProvinciaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalidadPostDto {
    private String nombre;
    private Long idProvincia;
}
