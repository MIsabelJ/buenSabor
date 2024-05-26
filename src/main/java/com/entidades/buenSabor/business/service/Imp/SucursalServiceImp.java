package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.DomicilioService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Domicilio;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SucursalServiceImp extends BaseServiceImp<Sucursal,Long> implements SucursalService {
    @Autowired
    DomicilioService domicilioService;
    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public Sucursal create (Sucursal sucursal){
        Domicilio domicilioSaved = domicilioService.create(sucursal.getDomicilio());
        sucursal.setDomicilio(domicilioSaved);
        return sucursalRepository.save(sucursal);

    }
    @Override
    public List<Categoria> getCategoriasBySucursalId(Long idSucursal){
        return sucursalRepository.getCategoriasBySucursalId(idSucursal);
    }
}
