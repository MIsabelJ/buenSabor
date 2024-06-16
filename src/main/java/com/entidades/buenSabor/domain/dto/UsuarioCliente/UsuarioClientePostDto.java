package com.entidades.buenSabor.domain.dto.UsuarioCliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioClientePostDto {
    private String email;
    private String userName;
    private String password;
}
