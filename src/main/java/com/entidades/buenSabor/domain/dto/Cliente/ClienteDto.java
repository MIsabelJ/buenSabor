package com.entidades.buenSabor.domain.dto.Cliente;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoShortDto;
import com.entidades.buenSabor.domain.entities.*;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.NotAudited;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClienteDto extends BaseDto{
    private String nombre;
    private String apellido;
    private String telefono;

    private UsuarioCliente usuarioCliente;

    private Set<DomicilioDto> domicilios;
    private Set<PedidoShortDto> pedidos;
}
