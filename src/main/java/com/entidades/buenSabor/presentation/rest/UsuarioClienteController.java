package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.service.UsuarioClienteService;
import com.entidades.buenSabor.business.service.UsuarioEmpleadoService;
import com.entidades.buenSabor.domain.entities.UsuarioCliente;
import com.entidades.buenSabor.repositories.UsuarioClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RestController
@RequestMapping("/usuario-cliente")
@CrossOrigin("*")
public class UsuarioClienteController {
    @Autowired
    UsuarioClienteService usuarioClienteService;

    @PostMapping
    public ResponseEntity<UsuarioCliente> post(@RequestBody UsuarioCliente usuarioCliente) {
        return ResponseEntity.ok().body(usuarioClienteService.create(usuarioCliente));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioCliente>> getAll() {
        return ResponseEntity.ok().body(usuarioClienteService.getAll());
    }
}
