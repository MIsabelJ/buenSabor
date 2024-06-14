package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.UsuarioClienteService;
import com.entidades.buenSabor.domain.entities.UsuarioCliente;
import com.entidades.buenSabor.repositories.UsuarioClienteRepository;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioClienteServiceImp extends BaseServiceImp<UsuarioCliente, Long> implements UsuarioClienteService {

    @Autowired
    private UsuarioClienteRepository usuarioClienteRepository;

    public UsuarioCliente compareUsuarios(UsuarioCliente usuario){
        List<UsuarioCliente> usuarios = usuarioClienteRepository.getAll();
        for (UsuarioCliente u: usuarios){
            if (u.getPassword().equals(usuario.getPassword()) && u.getUserName().equals(usuario.getUserName())){
                return u;
            }
        }
        return null;
    }

}
