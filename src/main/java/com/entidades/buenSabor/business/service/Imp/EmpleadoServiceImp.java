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
        String passwordHash = getMd5Password(empleado.getUsuarioEmpleado().getPassword());
        empleado.getUsuarioEmpleado().setPassword(passwordHash);
        return empleadoRepository.save(empleado);
    }

    public String getMd5Password(String password) {
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
