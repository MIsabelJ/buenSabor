package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.EmpleadoFacadeImp;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController extends BaseControllerImp<Empleado, EmpleadoDto, EmpleadoPostDto, EmpleadoPostDto, Long, EmpleadoFacadeImp> {
    public EmpleadoController(EmpleadoFacadeImp facade) {
        super(facade);
    }
}
