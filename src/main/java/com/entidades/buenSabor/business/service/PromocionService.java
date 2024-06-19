package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Promocion;
import org.mapstruct.Named;

public interface PromocionService extends BaseService<Promocion, Long> {
    @Named("findOrNull")
    public Promocion findOrNull(Long id);
}
