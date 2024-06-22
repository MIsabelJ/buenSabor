package com.entidades.buenSabor.domain.dto.Pedido;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteShortDto;
import com.entidades.buenSabor.domain.dto.DetallePedido.DetallePedidoDto;
import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalShortDto;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PedidoShortDto extends BaseDto {
    @Schema(type = "string", format = "time", pattern = "HH:mm:ss", description = "Horario de apertura en formato HH:mm:ss")
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private DomicilioDto domicilio;
    private SucursalShortDto sucursal;
    private FacturaDto factura;
    private Set<DetallePedidoDto> detallePedidos = new HashSet<>();
}
