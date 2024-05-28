package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloManufacturadoDetalleService;
import com.entidades.buenSabor.business.service.ArticuloManufacturadoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
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
    public ArticuloManufacturado create(ArticuloManufacturado request){
        Set<ArticuloManufacturadoDetalle> detalles = request.getArticuloManufacturadoDetalles();
        List<ImagenArticulo> imagenes = request.getImagenes();
        detalles.forEach(articuloManufacturadoDetalleService::create);
        imagenes.forEach(imagenArticuloRepository::save);
        return super.create(request);
    }
}
