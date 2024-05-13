package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.Sucursal;

public interface SucursalService  extends BaseService<Sucursal, Long> {
    Sucursal guardarSucursal(Sucursal sucursal);
    Sucursal actualizarSucursal(Long id,Sucursal sucursal);
}

