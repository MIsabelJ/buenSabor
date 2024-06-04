package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.FacturaFacadeImp;
import com.entidades.buenSabor.domain.dto.Factura.FacturaDto;
import com.entidades.buenSabor.domain.dto.Factura.FacturaPostDto;
import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/factura")
@CrossOrigin("*")
public class FacturaController extends BaseControllerImp<Factura, FacturaDto, FacturaPostDto, FacturaPostDto, Long, FacturaFacadeImp> {
    public FacturaController(FacturaFacadeImp facade) {
        super(facade);
    }
}
