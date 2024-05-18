package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ImagenPromocionFacadeImp;
import com.entidades.buenSabor.domain.dto.ImagenPromocion.ImagenPromocionDto;
import com.entidades.buenSabor.domain.dto.ImagenPromocion.ImagenPromocionPostDto;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/imagen-promocion")
@CrossOrigin("*")
public class ImagenPromocionController extends BaseControllerImp<ImagenPromocion, ImagenPromocionDto, ImagenPromocionPostDto, ImagenPromocionPostDto, Long, ImagenPromocionFacadeImp> {
    public ImagenPromocionController(ImagenPromocionFacadeImp facade) {
        super(facade);
    }
}
