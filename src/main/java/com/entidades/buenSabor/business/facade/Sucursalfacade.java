package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;

import java.util.List;
import java.util.Set;

public interface Sucursalfacade extends BaseFacade<SucursalDto, SucursalPostDto, SucursalUpdateDto, Long> {
    public List<CategoriaDto> findCategoriaBySucursalId(Long idSucursal);

}
