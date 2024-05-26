package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Empresa;
import com.entidades.buenSabor.domain.entities.Sucursal;

import java.util.List;

public interface EmpresaService extends BaseService<Empresa, Long> {
    public Empresa addSucursal(Long idEmpresa, Long idSucursal);

    public List<Sucursal> getSucursalesByEmpresaId(Long id);
}
