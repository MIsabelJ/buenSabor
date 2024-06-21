package com.entidades.buenSabor.domain.dto.Cliente;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import com.entidades.buenSabor.domain.entities.UsuarioCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteShortDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;

    private UsuarioCliente usuarioCliente;

    private Set<DomicilioDto> domicilios;
}
