package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;

import com.entidades.buenSabor.domain.dto.Empresa.EmpresaDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaLargeDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaPostDto;


public interface EmpresaFacade extends BaseFacade<EmpresaDto,EmpresaPostDto, EmpresaPostDto, Long> {
    EmpresaLargeDto addSucursal(Long idEmpresa, Long idSucursal);

    EmpresaLargeDto getEmpresaLarge(Long idEmpresa);

}
