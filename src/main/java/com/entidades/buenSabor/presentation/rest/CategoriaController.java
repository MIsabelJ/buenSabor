package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.CategoriaFacadeImp;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaDto;
import com.entidades.buenSabor.domain.dto.Categoria.CategoriaPostDto;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
