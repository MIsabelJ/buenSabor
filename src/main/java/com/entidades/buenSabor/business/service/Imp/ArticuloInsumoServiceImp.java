package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloInsumoServiceImp extends BaseServiceImp<ArticuloInsumo, Long> implements ArticuloInsumoService {
    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;

    @Override
    public ArticuloInsumo create(ArticuloInsumo request){
        List<ImagenArticulo> imagesToSave = request.getImagenes();
        imagesToSave.forEach(imagenArticuloRepository::save);
        return super.create(request);
    }
}
