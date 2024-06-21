package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.ClienteService;
import com.entidades.buenSabor.business.service.DomicilioService;
import com.entidades.buenSabor.business.service.ImagenClienteService;
import com.entidades.buenSabor.domain.entities.Cliente;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import com.entidades.buenSabor.repositories.ClienteRepository;
import com.entidades.buenSabor.repositories.ImagenClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClienteServiceImp extends BaseServiceImp<Cliente, Long> implements ClienteService {
    @Autowired
    ImagenClienteRepository imagenClienteRepository;
    @Autowired
    DomicilioService domicilioService;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    @Override
    public Cliente create(Cliente cliente){
        Set<Domicilio> domicilios = cliente.getDomicilios();
        for (Domicilio domicilioToSave: domicilios) {
            cliente.getDomicilios().add(domicilioService.create(domicilioToSave));
        }

        return super.create(cliente);
    }

    @Override
    public Cliente fyndByUserId(Long id){
        return clienteRepository.findByUserId(id);
    }
}
