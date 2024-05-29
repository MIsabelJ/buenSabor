package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloInsumoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articulo-insumo")
@CrossOrigin("*")
public class ArticuloInsumoController extends BaseControllerImp<ArticuloInsumo, ArticuloInsumoDto, ArticuloInsumoPostDto, ArticuloInsumoPostDto, Long, ArticuloInsumoFacadeImp> {
    public ArticuloInsumoController(ArticuloInsumoFacadeImp facade) {
        super(facade);
    }

}
