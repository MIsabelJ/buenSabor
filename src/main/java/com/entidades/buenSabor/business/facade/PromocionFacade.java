package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionPostDto;
import com.entidades.buenSabor.domain.entities.Promocion;

import java.util.List;

public interface PromocionFacade extends BaseFacade<PromocionDto, PromocionPostDto, PromocionPostDto, Long> {
    public List<PromocionDto> findAllPromocionesVenta();
}
