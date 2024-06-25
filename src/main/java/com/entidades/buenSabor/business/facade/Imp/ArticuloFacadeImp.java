package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticuloFacadeImp extends BaseFacadeImp<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto, Long> implements ArticuloFacade {
    public ArticuloFacadeImp(BaseService<Articulo, Long> baseService, BaseMapper<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    ArticuloService articuloService;

    @Override
    public List<ArticuloDto> getAll(){
        List<Articulo> articulos = articuloService.getAll();

        return articulos.stream()
                .map(articulo -> {
                    if (articulo instanceof ArticuloInsumo) {
                        return articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                    } else if (articulo instanceof ArticuloManufacturado) {
                        return articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                    } else {
                        throw new RuntimeException("Tipo de artículo no soportado: " + articulo.getClass().getName());
                    }
                })
                .collect(Collectors.toList());
    }

    @Override
    public ArticuloDto instanceArticulo(Articulo articulo){
        if(articulo instanceof ArticuloManufacturado){
            return articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
        }else if(articulo instanceof ArticuloInsumo){
            return articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
        }
        return null;
    }

    @Override
    public List<ArticuloDto> getAllArticulosVenta(){
        List<Articulo> articulos = articuloService.getAllArticulosVenta();

        return articulos.stream()
                .filter(articulo -> {
                    if (articulo instanceof ArticuloManufacturado) {
                        ArticuloManufacturado am = (ArticuloManufacturado) articulo;
                        // Verifica si todos los insumos tienen stock suficiente
                        return am.getArticuloManufacturadoDetalles().stream()
                                .allMatch(amd -> amd.getArticuloInsumo().getStockActual() > amd.getCantidad());
                    }
                    return true; // Incluye los otros tipos de artículos sin filtrar
                })
                .map(articulo -> {
                    if (articulo instanceof ArticuloInsumo) {
                        return articuloInsumoMapper.toDTO((ArticuloInsumo) articulo);
                    } else if (articulo instanceof ArticuloManufacturado) {
                        return articuloManufacturadoMapper.toDTO((ArticuloManufacturado) articulo);
                    } else {
                        throw new RuntimeException("Tipo de artículo no soportado: " + articulo.getClass().getName());
                    }
                })
                .collect(Collectors.toList());
    }
}
