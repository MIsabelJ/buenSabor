package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.PromocionDetalleService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.domain.entities.PromocionDetalle;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.ImagenPromocionRepository;
import com.entidades.buenSabor.repositories.PromocionRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    @Autowired
    SucursalRepository sucursalRepository;
    @Override
    @Transactional
    public Promocion create(Promocion request){
        Set<PromocionDetalle> detalles = request.getPromocionDetalles();
        List<ImagenPromocion> imagenes = request.getImagenes();
        Set<Sucursal> sucursales = request.getSucursales();
        for (Sucursal sucursal: sucursales) {
            sucursal.getPromociones().add(request);
        }
        request.setSucursales(sucursales);
        detalles.forEach(promocionDetalleService::create);
        imagenes.forEach(imagenPromocionRepository::save);
        return promocionRepository.save(request);
    }

    @Override
    @Transactional
    public Promocion update(Promocion request, Long id) {
        Promocion promocionToUpdate = promocionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada"));

        Set<PromocionDetalle> detallesToSave = request.getPromocionDetalles();
        Set<Sucursal> sucursalesToSave = request.getSucursales();

        // Actualiza detalles de promoción
        if (detallesToSave != null) {
            for (PromocionDetalle detalle : detallesToSave) {
                if (detalle.getId() == null) {
                    promocionDetalleService.create(detalle);
                }
            }
            promocionToUpdate.setPromocionDetalles(detallesToSave);
        }

        // Actualiza imágenes de promoción
        List<ImagenPromocion> imagesToSave = request.getImagenes();
        if (imagesToSave != null) {
            for (ImagenPromocion imagen : imagesToSave) {
                imagenPromocionRepository.save(imagen);
            }
            promocionToUpdate.setImagenes(imagesToSave);
        }

        // Actualiza sucursales
        if (sucursalesToSave != null) {
            Set<Sucursal> managedSucursales = new HashSet<>();
            for (Sucursal sucursal : sucursalesToSave) {
                Sucursal managedSucursal = sucursalRepository.findById(sucursal.getId())
                        .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
                managedSucursal.getPromociones().add(promocionToUpdate);  // Añadir la promoción a la sucursal
                managedSucursales.add(managedSucursal);
            }
            promocionToUpdate.setSucursales(managedSucursales);
        }

        return promocionRepository.save(promocionToUpdate);
    }

    @Override
    public Promocion findOrNull(Long id){
        if (id == 0){
            return null;
        }else {
            return super.getById(id);
        }
    }
}
