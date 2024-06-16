package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.EmpleadoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.EmpleadoMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.business.service.EmpleadoService;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoDto;
import com.entidades.buenSabor.domain.dto.Empleado.EmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoFacadeImp extends BaseFacadeImp<Empleado, EmpleadoDto, EmpleadoPostDto, EmpleadoPostDto, Long> implements EmpleadoFacade{
    public EmpleadoFacadeImp(BaseService<Empleado, Long> baseService, BaseMapper<Empleado, EmpleadoDto, EmpleadoPostDto, EmpleadoPostDto> baseMapper) {
        super(baseService, baseMapper);
    }

    @Autowired
    EmpleadoMapper empleadoMapper;
    @Autowired
    EmpleadoService empleadoService;

    @Override
    public EmpleadoDto getEmpleadoByEmail(String email){
        return empleadoMapper.toDTO(empleadoService.findByEmail(email));
    }
}
