package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.CategoriaFacadeImp;
import com.entidades.buenSabor.domain.dto.Articulo.ArticuloDto;
import com.entidades.buenSabor.domain.dto.ArticuloInsumo.ArticuloInsumoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import jakarta.servlet.annotation.HandlesTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
public class CategoriaController extends BaseControllerImp<Categoria, CategoriaDto, CategoriaPostDto, CategoriaPostDto, Long, CategoriaFacadeImp> {
    public CategoriaController(CategoriaFacadeImp facade) {
        super(facade);
    }

   @PutMapping("/addSubCategoria/{idCategoria}")
    public ResponseEntity<CategoriaDto> addSubCategoria(@PathVariable Long idCategoria, @RequestBody CategoriaPostDto subCategoria){
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.addSubCategoria(idCategoria,subCategoria));
    }

    @GetMapping("/{idCategoria}/insumos")
    public ResponseEntity<List<ArticuloInsumoDto>> getInsumosByCategoriaId(@PathVariable Long idCategoria){
        return ResponseEntity.ok(facade.getInsumoByCategoriaId(idCategoria));
    }

    @GetMapping("/{idCategoria}/manufacturados")
    public ResponseEntity<List<ArticuloManufacturadoDto>> getManufacturadosByCategoriaId(@PathVariable Long idCategoria){
        return ResponseEntity.ok(facade.getManufacturadoByCategoriaId(idCategoria));
    }

    @GetMapping("/{idCategoria}/articulos")
    public ResponseEntity<List<ArticuloDto>> getArticulosByCategoriaId(@PathVariable Long idCategoria){
        return ResponseEntity.ok(facade.getArticulosByCategoriaId(idCategoria));
    }
}
