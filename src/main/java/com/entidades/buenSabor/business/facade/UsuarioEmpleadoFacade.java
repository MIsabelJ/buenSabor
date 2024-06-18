package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoDto;
import com.entidades.buenSabor.domain.dto.UsuarioEmpleado.UsuarioEmpleadoPostDto;
import com.entidades.buenSabor.domain.entities.UsuarioEmpleado;

public interface UsuarioEmpleadoFacade extends BaseFacade<UsuarioEmpleadoDto, UsuarioEmpleadoPostDto, UsuarioEmpleadoPostDto, Long> {
}
