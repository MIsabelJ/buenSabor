package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.DomicilioService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.repositories.SucursalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SucursalServiceImp extends BaseServiceImp<Sucursal,Long> implements SucursalService {
    @Autowired
    DomicilioService domicilioService;
    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    @Transactional
    public Sucursal create (Sucursal sucursal){
        Domicilio domicilioSaved = domicilioService.create(sucursal.getDomicilio());
        sucursal.setDomicilio(domicilioSaved);
        return sucursalRepository.save(sucursal);

    }
    @Override
    public List<Categoria> getCategoriasBySucursalId(Long idSucursal){
        return sucursalRepository.getCategoriasBySucursalId(idSucursal);
    }

    @Override
    public List<Promocion> getPromocionesBySucursalId(Long idSucursal){
        return sucursalRepository.getPromocionBySucursalId(idSucursal);
    }
    @Override
    public List<Pedido> getPedidosBySucursalId(Long idSucursal){
        return sucursalRepository.getPedidoBySucursalId(idSucursal);
    }
    @Override
    public List<Articulo> getArticulosBySucursalId(Long idSucursal){
        return sucursalRepository.getArticuloBySucursalId(idSucursal);
    }
    @Override
    public List<ArticuloInsumo> getArticuloInsumosBySucursalId(Long idSucursal){
        return sucursalRepository.getInsumoBySucursalId(idSucursal);
    }
    @Override
    public List<ArticuloManufacturado> getArticuloManufacturadosBySucursalId(Long idSucursal){
        return sucursalRepository.getManufacturadoBySucursalId(idSucursal);
    }

}
