package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;

import java.util.List;
import java.util.Set;

public interface SucursalService  extends BaseService<Sucursal, Long> {
    public List<Categoria> getCategoriasBySucursalId(Long idSucursal);


}

