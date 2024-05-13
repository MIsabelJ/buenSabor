package com.entidades.buenSabor.business.mapper;


import com.entidades.buenSabor.domain.dto.Empresa.EmpresaDto;

import com.entidades.buenSabor.domain.dto.Empresa.EmpresaLargeDto;
import com.entidades.buenSabor.domain.dto.Empresa.EmpresaPostDto;
import com.entidades.buenSabor.domain.entities.Empresa;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmpresaMapper extends BaseMapper<Empresa, EmpresaDto, EmpresaPostDto, EmpresaPostDto> {


    EmpresaLargeDto toLargeDto(Empresa empresa);
    EmpresaPostDto toPostDto(Empresa empresa);

}
