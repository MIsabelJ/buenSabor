package com.entidades.buenSabor.business.facade.Imp;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.business.facade.UsuarioEmpleadoFacade;
import com.entidades.buenSabor.business.mapper.BaseMapper;
import com.entidades.buenSabor.business.mapper.UsuarioEmpleadoMapper;
import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.UsuarioEmpleado;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEmpleadoFacadeImp extends BaseFacadeImp<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoPostDto, UsuarioEmpleadoPostDto, Long>{
    public UsuarioEmpleadoFacadeImp(BaseService<UsuarioEmpleado, Long> baseService, BaseMapper<UsuarioEmpleado, UsuarioEmpleadoDto, UsuarioEmpleadoPostDto, UsuarioEmpleadoPostDto> baseMapper) {
        super(baseService, baseMapper);
    }
}
