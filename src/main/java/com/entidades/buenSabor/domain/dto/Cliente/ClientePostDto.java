package com.entidades.buenSabor.domain.dto.Cliente;

import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioPostDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.dto.UsuarioCliente.UsuarioClientePostDto;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import com.entidades.buenSabor.domain.entities.UsuarioCliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ClientePostDto {
    private String nombre;
    private String apellido;
    private String telefono;

    private UsuarioClientePostDto usuarioCliente;

    private Set<DomicilioPostDto> domicilios;
    private Set<PedidoPostDto> pedidos;
}
