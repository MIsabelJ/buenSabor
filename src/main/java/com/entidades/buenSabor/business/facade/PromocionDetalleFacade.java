package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionPostDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetallePostDto;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;

public interface PromocionDetalleFacade extends BaseFacade<PromocionDetalleDto, PromocionDetallePostDto, PromocionDetallePostDto, Long> {
}
