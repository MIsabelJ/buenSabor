package com.entidades.buenSabor.business.facade;

import com.entidades.buenSabor.business.facade.Base.BaseFacade;
import com.entidades.buenSabor.domain.dto.Cliente.ClienteDto;
import com.entidades.buenSabor.domain.dto.Cliente.ClientePostDto;
import com.entidades.buenSabor.domain.entities.Cliente;

public interface ClienteFacade extends BaseFacade<ClienteDto, ClientePostDto, ClientePostDto, Long> {
}
