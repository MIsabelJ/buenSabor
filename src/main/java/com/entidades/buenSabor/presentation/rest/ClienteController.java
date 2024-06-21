package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ClienteFacadeImp;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClientePostDto;
import com.entidades.buenSabor.domain.entities.Cliente;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController extends BaseControllerImp<Cliente, ClienteDto, ClientePostDto, ClientePostDto, Long, ClienteFacadeImp> {
    private final ClienteFacadeImp clienteFacadeImp;

    public ClienteController(ClienteFacadeImp facade, ClienteFacadeImp clienteFacadeImp) {
        super(facade);
        this.clienteFacadeImp = clienteFacadeImp;
    }

    @GetMapping("/findCliente/{id}")
    public ResponseEntity<ClienteDto> findClienteByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteFacadeImp.getClienteById(id));
    }
}
