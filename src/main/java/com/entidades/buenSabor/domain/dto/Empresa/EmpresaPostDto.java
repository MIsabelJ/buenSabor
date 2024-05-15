package com.entidades.buenSabor.domain.dto.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmpresaPostDto {
    private String nombre;
    private String razonSocial;
    private Long cuil;
}
