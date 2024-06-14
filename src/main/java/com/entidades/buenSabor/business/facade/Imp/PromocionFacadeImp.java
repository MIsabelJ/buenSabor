package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.ArticuloFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.PromocionFacade;
import com.entidades.buenSabor.business.mapper.ArticuloInsumoMapper;
import com.entidades.buenSabor.business.mapper.ArticuloManufacturadoMapper;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.PromocionMapperImpl;
import com.entidades.buenSabor.business.service.ArticuloService;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.PromocionService;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionPostDto;
import com.entidades.buenSabor.domain.dto.PromocionDetalle.PromocionDetalleDto;
import com.entidades.buenSabor.domain.entities.*;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PromocionFacadeImp extends BaseFacadeImp<Promocion, PromocionDto, PromocionPostDto, PromocionPostDto, Long> implements PromocionFacade {
    @Autowired
    private PromocionMapperImpl promocionMapperImpl;

    public PromocionFacadeImp(BaseService<Promocion, Long> baseService, BaseMapper<Promocion, PromocionDto, PromocionPostDto, PromocionPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    PromocionService promocionService;
    @Autowired
    ArticuloManufacturadoMapper articuloManufacturadoMapper;
    @Autowired
    ArticuloInsumoMapper articuloInsumoMapper;
    @Autowired
    ArticuloFacade articuloFacade;

    @Override
    public List<PromocionDto> getAll() {
        List<Promocion> promociones = promocionService.getAll();  // Asumiendo que promocionService tiene un método getAll()
        List<PromocionDto> promocionDtos = new ArrayList<>();

        for (Promocion promocion : promociones) {
            PromocionDto promocionDto = promocionMapperImpl.toDTO(promocion);
            Set<PromocionDetalleDto> promocionDetalleDtos = new HashSet<>();

            for (PromocionDetalle detalle : promocion.getPromocionDetalles()) {
                PromocionDetalleDto promocionDetalleDto = new PromocionDetalleDto();
                promocionDetalleDto.setCantidad(detalle.getCantidad());
                promocionDetalleDto.setEliminado(detalle.isEliminado());
                promocionDetalleDto.setId(detalle.getId());
                promocionDetalleDto.setArticulo(articuloFacade.instanceArticulo(detalle.getArticulo()));
                promocionDetalleDtos.add(promocionDetalleDto);
            }

            promocionDto.setPromocionDetalles(promocionDetalleDtos);
            promocionDtos.add(promocionDto);
        }

        return promocionDtos;
    }

    @Override
    public PromocionDto getById(Long id) {
        Promocion promocion = promocionService.getById(id);
        PromocionDto promocionDto = promocionMapperImpl.toDTO(promocion);
        Set<PromocionDetalleDto> promocionDetalleDtos = new HashSet<>();
        for (PromocionDetalle detalle: promocion.getPromocionDetalles()) {
            PromocionDetalleDto promocionDetalleDto = new PromocionDetalleDto();
            promocionDetalleDto.setCantidad(detalle.getCantidad());
            promocionDetalleDto.setEliminado(detalle.isEliminado());
            promocionDetalleDto.setId(detalle.getId());
            promocionDetalleDto.setArticulo(articuloFacade.instanceArticulo(detalle.getArticulo()));
            promocionDetalleDtos.add(promocionDetalleDto);
        }
        promocionDto.setPromocionDetalles(promocionDetalleDtos);
        return promocionDto;

    }
}
