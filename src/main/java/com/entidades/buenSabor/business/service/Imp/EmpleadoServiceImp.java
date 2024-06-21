package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.entities.Empleado;
import com.entidades.buenSabor.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EmpleadoServiceImp extends BaseServiceImp<Empleado, Long> implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Empleado findByEmail(String email){
        return empleadoRepository.findByEmail(email);
    }

    @Override
    public Empleado create(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
}
