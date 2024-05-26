package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PromocionFacadeImp;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionPostDto;
import com.entidades.buenSabor.domain.entities.Promocion;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/promocion")
@CrossOrigin("*")
public class PromocionController extends BaseControllerImp<Promocion, PromocionDto, PromocionPostDto, PromocionPostDto, Long, PromocionFacadeImp> {
    public PromocionController(PromocionFacadeImp facade) {
        super(facade);
    }
}
