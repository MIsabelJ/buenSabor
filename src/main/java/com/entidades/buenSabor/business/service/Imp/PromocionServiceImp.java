package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionDetalleService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PromocionServiceImp extends BaseServiceImp<Promocion, Long> implements PromocionService {
    @Autowired
    PromocionRepository promocionRepository;
    @Autowired
    PromocionDetalleService promocionDetalleService;
    @Override
    public Promocion create(Promocion request){
        Set<PromocionDetalle> detalles = request.getPromocionDetalles();
        detalles.forEach(promocionDetalleService::create);
        return promocionRepository.save(request);
    }
}
