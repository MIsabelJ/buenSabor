package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionDetalleService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.repositories.ImagenPromocionRepository;
import com.entidades.buenSabor.repositories.PromocionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PromocionServiceImp extends BaseServiceImp<Promocion, Long> implements PromocionService {
    @Autowired
    PromocionRepository promocionRepository;
    @Autowired
    PromocionDetalleService promocionDetalleService;
    @Autowired
    ImagenPromocionRepository imagenPromocionRepository;
    @Override
    @Transactional
    public Promocion create(Promocion request){
        Set<PromocionDetalle> detalles = request.getPromocionDetalles();
        List<ImagenPromocion> imagenes = request.getImagenes();
        detalles.forEach(promocionDetalleService::create);
        imagenes.forEach(imagenPromocionRepository::save);
        return promocionRepository.save(request);
    }

    @Override
    @Transactional
    public Promocion update(Promocion request, Long id){
        Promocion promocionToUpdate = baseRepository.getById(id);
        Set<PromocionDetalle> detallesToSave = request.getPromocionDetalles();
        if(detallesToSave != null){
            for(PromocionDetalle detalle : detallesToSave){
                if(detalle.getId() == null){
                    promocionDetalleService.create(detalle);
                }
            }
            promocionToUpdate.setPromocionDetalles(detallesToSave);
        }
        List<ImagenPromocion> imagesToSave = request.getImagenes();
        if(imagesToSave != null){
            for(ImagenPromocion imagen : imagesToSave){
                imagenPromocionRepository.save(imagen);
            }
            promocionToUpdate.setImagenes(imagesToSave);
        }
        return promocionRepository.save(promocionToUpdate);
    }
}
