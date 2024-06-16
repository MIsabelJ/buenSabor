package com.entidades.buenSabor.domain.dto.UsuarioCliente;

import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioClienteDto extends BaseDto {

    private String email;
    private String userName;
    private String password;
}
