package com.entidades.buenSabor.presentation.rest;


import com.entidades.buenSabor.business.facade.Imp.SucursalFacadeImp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Promocion.PromocionDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalDto;

import com.entidades.buenSabor.domain.dto.Sucursal.SucursalPostDto;
import com.entidades.buenSabor.domain.dto.Sucursal.SucursalUpdateDto;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin("*")
public class SucursalController extends BaseControllerImp<Sucursal, SucursalDto, SucursalPostDto, SucursalUpdateDto, Long, SucursalFacadeImp> {

    public SucursalController(SucursalFacadeImp facade) {
        super(facade);
    }
    @GetMapping("/{idSucursal}/categorias")
    public ResponseEntity<List<CategoriaDto>> getCategoriasBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findCategoriaBySucursalId(idSucursal));
    }

    @GetMapping("/{idSucursal}/promociones")
    public ResponseEntity<List<PromocionDto>> getPromocionesBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findPromocionesBySucursalId(idSucursal));
    }

    @GetMapping("/{idSucursal}/pedidos")
    public ResponseEntity<List<PedidoDto>> getPedidosBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findPedidosBySucursalId(idSucursal));
    }

    @GetMapping("/{idSucursal}/articulos")
    public ResponseEntity<List<ArticuloDto>> getArticuloBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findArticulosBySucursalId(idSucursal));
    }

    @GetMapping("/{idSucursal}/insumos")
    public ResponseEntity<List<ArticuloInsumoDto>> getInsumoBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findArticuloInsumosBySucursalId(idSucursal));
    }

    @GetMapping("/{idSucursal}/manufacturados")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getManufacturadoBySucursalId(@PathVariable Long idSucursal){
        return ResponseEntity.ok(facade.findArticuloManufacturadosBySucursalId(idSucursal));
    }

}
