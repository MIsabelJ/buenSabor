package com.entidades.buenSabor.domain.dto.UsuarioEmpleado;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEmpleadoPostDto {
    private String email;
    private String userName;
}
