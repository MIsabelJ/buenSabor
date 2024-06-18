package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.UsuarioEmpleadoFacadeImp;
import com.entidades.buenSabor.business.service.UsuarioEmpleadoService;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.UsuarioEmpleado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/usuario-empleado")
@CrossOrigin("*")
public class UsuarioEmpleadoController extends BaseControllerImp<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoPostDto, UsuarioEmpleadoPostDto, Long, UsuarioEmpleadoFacadeImp> {
    public UsuarioEmpleadoController(UsuarioEmpleadoFacadeImp facade) {
        super(facade);
    }
}
