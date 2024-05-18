package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoDetalleFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetalleDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetallePostDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturadoDetalle;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articulo-manufacturado-detalle")
@CrossOrigin("*")
public class ArticuloManufacturadoDetalleController extends BaseControllerImp<ArticuloManufacturadoDetalle, ArticuloManufacturadoDetalleDto, ArticuloManufacturadoDetallePostDto, ArticuloManufacturadoDetallePostDto, Long, ArticuloManufacturadoDetalleFacadeImp> {
    public ArticuloManufacturadoDetalleController(ArticuloManufacturadoDetalleFacadeImp facade) {
        super(facade);
    }
}
