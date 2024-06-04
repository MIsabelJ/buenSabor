package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloManufacturadoDetalleService;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticuloManufacturadoServiceImp extends BaseServiceImp<ArticuloManufacturado, Long> implements ArticuloManufacturadoService {
    @Autowired
    ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    ArticuloManufacturadoDetalleService articuloManufacturadoDetalleService;
    @Autowired
    ImagenArticuloRepository imagenArticuloRepository;

    @Override
    @Transactional
    public ArticuloManufacturado create(ArticuloManufacturado request){
        Set<ArticuloManufacturadoDetalle> detalles = request.getArticuloManufacturadoDetalles();
        List<ImagenArticulo> imagenes = request.getImagenes();
        imagenes.forEach(imagenArticuloRepository::save);
        for (ArticuloManufacturadoDetalle detalleToSave: detalles) {
            request.getArticuloManufacturadoDetalles().add(articuloManufacturadoDetalleService.create(detalleToSave));
        }
        return super.create(request);
    }

    @Override
    @Transactional
    public ArticuloManufacturado update(ArticuloManufacturado request, Long id){
        ArticuloManufacturado manufacturadoToUpdate = baseRepository.getById(id);
        Set<ArticuloManufacturadoDetalle> detallesToSave = request.getArticuloManufacturadoDetalles();
        if(detallesToSave != null){
            for (ArticuloManufacturadoDetalle detalle : detallesToSave){
                if (detalle.getId() == null){
                    articuloManufacturadoDetalleService.create(detalle);
                }
            }
            manufacturadoToUpdate.setArticuloManufacturadoDetalles(detallesToSave);
        }
        List<ImagenArticulo> imagesToSave = request.getImagenes();
        if (imagesToSave != null){
            for (ImagenArticulo imagen : imagesToSave){
                imagenArticuloRepository.save(imagen);
            }
            manufacturadoToUpdate.setImagenes(imagesToSave);
        }

        return articuloManufacturadoRepository.save(manufacturadoToUpdate);
    }
}
