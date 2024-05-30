package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticuloInsumoServiceImp extends BaseServiceImp<ArticuloInsumo, Long> implements ArticuloInsumoService {
    @Autowired
    private ImagenArticuloRepository imagenArticuloRepository;
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    @Override
    @Transactional
    public ArticuloInsumo create(ArticuloInsumo request){
        List<ImagenArticulo> imagesToSave = request.getImagenes();
        imagesToSave.forEach(imagenArticuloRepository::save);
        return super.create(request);
    }

    @Override
    @Transactional
    public ArticuloInsumo update(ArticuloInsumo request, Long id) {
        ArticuloInsumo insumoToUpdate = baseRepository.getById(id);

        // Asignar las nuevas imágenes al ArticuloInsumo
        List<ImagenArticulo> imagesToSave = request.getImagenes();
        if (imagesToSave != null) {
            for (ImagenArticulo imagen : imagesToSave) {
                imagenArticuloRepository.save(imagen);
            }
            insumoToUpdate.setImagenes(imagesToSave);
        }

        // Guardar el ArticuloInsumo actualizado
        return articuloInsumoRepository.save(insumoToUpdate);
    }
}
