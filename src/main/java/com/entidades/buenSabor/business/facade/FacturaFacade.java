package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaPostDto;

public interface FacturaFacade extends BaseFacade<FacturaDto, FacturaPostDto, FacturaPostDto, Long> {
}
