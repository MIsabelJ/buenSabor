package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import com.entidades.buenSabor.repositories.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria, Long> implements CategoriaService {

    @Autowired
    SucursalService sucursalService;
/*    @Autowired
    CategoriaService categoriaService;*/

    @Override
    public Categoria create(Categoria request) {
        Set<Sucursal> sucursales = request.getSucursales();
        var entitySaved = baseRepository.save(request);

        sucursales.stream()
                .map(sucursal -> {
                    sucursal.getCategorias().add(request);
                    return sucursal;
                })
                .forEach(sucursalService::create); // Suponiendo que sucursalService tiene un método save para guardar sucursales
        return entitySaved;
    }

/*    @Override
    public Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate) {
        var categoria = baseRepository.getById(idCategoria);
        create(subCategoriaToCreate);
        categoria.getSubCategorias().add(subCategoriaToCreate);
        return baseRepository.save(categoria);
    }*/
}
