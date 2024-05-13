package com.entidades.buenSabor.domain.dto.Provincia;

import com.entidades.buenSabor.domain.dto.Pais.PaisDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProvinciaPostDto {
    private String nombre;
    private PaisDto pais;
}
