package com.entidades.buenSabor.domain.dto.Pedido;

import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioPostDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PedidoPostDto {
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private DomicilioPostDto domicilio;
    private Long idSucursal;
    private FacturaDto factura;
    private Long idCliente;
    private Set<DetallePedidoDto> detallePedidos;
    private Long idEmpleado;
}
