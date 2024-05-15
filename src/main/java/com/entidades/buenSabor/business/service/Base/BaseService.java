package com.entidades.buenSabor.business.service.Base;

import com.entidades.buenSabor.domain.entities.Base;
import org.mapstruct.Named;

import java.io.Serializable;
import java.util.List;

public interface BaseService <E extends Base, ID extends Serializable>{
    public E create(E request);
    public List<E> getAll();
    public void deleteById(ID id);
    public E update(E request, ID id);

    //ANOTACION PARA QUE MAPSTRUCT PUEDA ACCEDER AL METODO DESDE EL MAPPER DE CADA DTO
    @Named("getById")
    public E getById(ID id);
}
