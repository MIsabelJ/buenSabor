package com.entidades.buenSabor.presentation.rest;


import com.entidades.buenSabor.business.facade.Imp.SucursalFacadeImp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;

import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImp<Sucursal, SucursalDto, SucursalPostDto, SucursalUpdateDto, Long, SucursalFacadeImp> {

    public SucursalController(SucursalFacadeImp facade) {
        super(facade);
    }

}
