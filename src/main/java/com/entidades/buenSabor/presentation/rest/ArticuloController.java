package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloFacadeImp;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloPostDto;
import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articulo")
@CrossOrigin("*")
public class ArticuloController extends BaseControllerImp<Articulo, ArticuloDto, ArticuloPostDto, ArticuloPostDto, Long, ArticuloFacadeImp> {
    public ArticuloController(ArticuloFacadeImp facade) {
        super(facade);
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<ArticuloDto>> findVenta() {
        return ResponseEntity.ok(facade.getAllArticulosVenta());
    }
}
