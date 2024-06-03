package com.entidades.buenSabor.domain.dto.Pedido;

import com.entidades.buenSabor.domain.dto.Domicilio.DomicilioDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.enums.Estado;
import com.entidades.buenSabor.domain.enums.FormaPago;
import com.entidades.buenSabor.domain.enums.TipoEnvio;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PedidoDto {
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    private DomicilioDto domicilio;
    private SucursalDto sucursal;
    private
}
