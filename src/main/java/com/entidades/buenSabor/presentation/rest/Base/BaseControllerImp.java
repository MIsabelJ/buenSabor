package com.entidades.buenSabor.presentation.rest.Base;

import com.entidades.buenSabor.business.facade.Base.BaseFacadeImp;
import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Controller
public abstract class BaseControllerImp <E extends Base,D extends BaseDto, ID extends Serializable, F extends BaseFacadeImp<E,D,ID>> implements BaseController<D,ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);
    protected F facade;
    public BaseControllerImp(F facade){
        this.facade = facade;
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable ID id){
        logger.info("INICIO GET BY ID {}",id);
        return ResponseEntity.ok(facade.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<D>> getAll() {
        logger.info("INICIO GET ALL");
        return ResponseEntity.ok(facade.getAll());
    }

    @PostMapping()
    public ResponseEntity<D> create(@RequestBody D entity){
        logger.info("INICIO CREATE {}",entity.getClass());
        return ResponseEntity.ok(facade.createNew(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> edit(@RequestBody D entity, @PathVariable ID id){
        logger.info("INICIO EDIT {}",entity.getClass());
        return ResponseEntity.ok(facade.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id){
        logger.info("INICIO DELETE BY ID");
        facade.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
