package com.entidades.buenSabor.domain.dto.Factura;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.enums.FormaPago;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FacturaDto extends BaseDto {
    private LocalDate fechaFacturacion;
    private Integer mpPaymentId;
    private Integer mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private Double totalVenta;
}
