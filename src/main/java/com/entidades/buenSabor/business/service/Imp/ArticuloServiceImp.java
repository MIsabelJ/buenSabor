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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticuloServiceImp extends BaseServiceImp<Articulo, Long> implements ArticuloService {

    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
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

    @Override
    public List<Articulo> getAll(){
        List<ArticuloInsumo> insumos = articuloInsumoRepository.findAll();
        List<ArticuloManufacturado> manufacturados = articuloManufacturadoRepository.findAll();

        List<Articulo> articulos = new ArrayList<>();
        articulos.addAll(insumos);
        articulos.addAll(manufacturados);

        return articulos;
    }

    @Override
    public List<Articulo> getAllArticulosVenta(){
        List<ArticuloInsumo> insumos = articuloInsumoRepository.findAllVenta();
        List<ArticuloManufacturado> manufacturados = articuloManufacturadoRepository.findAllVenta();

        List<Articulo> articulos = new ArrayList<>();
        articulos.addAll(insumos);
        articulos.addAll(manufacturados);

        return articulos;
    }

    @Override
    public Articulo findOrNull(Long id){
        if (id == 0){
            return null;
        }else{
            return getArticuloById(id);
        }
    }

}
