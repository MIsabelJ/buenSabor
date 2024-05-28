package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.ArticuloInsumoService;
import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.repositories.ArticuloInsumoRepository;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import org.mapstruct.Named;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticuloServiceImp extends BaseServiceImp<Articulo, Long> implements ArticuloService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    public Articulo getArticuloById(Long id) {
        Optional<ArticuloInsumo> insumoOpt = articuloInsumoRepository.findById(id);
        if (insumoOpt.isPresent()) {
            return insumoOpt.get();
        }

        Optional<ArticuloManufacturado> manufacturadoOpt = articuloManufacturadoRepository.findById(id);
        if (manufacturadoOpt.isPresent()) {
            return manufacturadoOpt.get();
        }

        throw new OpenApiResourceNotFoundException("Articulo not found with id " + id);
    }
}
