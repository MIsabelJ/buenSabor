package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDtoPost;
import com.entidades.buenSabor.domain.entities.Factura;

public interface FacturaFacade extends BaseFacade<FacturaDto, FacturaDtoPost, FacturaDtoPost, Long> {
}
