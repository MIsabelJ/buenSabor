package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.UsuarioEmpleadoService;
import com.entidades.buenSabor.domain.entities.UsuarioEmpleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/usuario-empleado")
@CrossOrigin("*")
public class UsuarioEmpleadoController {
    @Autowired
    private UsuarioEmpleadoService usuarioEmpleadoService;

    @PostMapping
    public ResponseEntity<UsuarioEmpleado> post(@RequestBody UsuarioEmpleado usuario) {
        return ResponseEntity.ok(usuarioEmpleadoService.create(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioEmpleado>> getAll() {
        return ResponseEntity.ok(usuarioEmpleadoService.getAll());
    }
}
