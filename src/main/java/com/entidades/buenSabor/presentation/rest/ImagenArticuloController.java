package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ImagenArticuloFacadeImp;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloPostDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen-articulo")
@CrossOrigin("*")
public class ImagenArticuloController extends BaseControllerImp<ImagenArticulo, ImagenArticuloDto, ImagenArticuloPostDto, ImagenArticuloPostDto, Long, ImagenArticuloFacadeImp> {
    public ImagenArticuloController(ImagenArticuloFacadeImp facade) {
        super(facade);
    }
}
