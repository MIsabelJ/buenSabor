package com.entidades.buenSabor.business.mapper;

import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDtoPost;
import com.entidades.buenSabor.domain.entities.Factura;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper extends BaseMapper<Factura, FacturaDto, FacturaDtoPost, FacturaDtoPost> {
}
