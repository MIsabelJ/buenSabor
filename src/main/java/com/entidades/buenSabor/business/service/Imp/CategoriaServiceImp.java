package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CategoriaService;
import com.entidades.buenSabor.business.service.SucursalService;
import com.entidades.buenSabor.domain.entities.*;
import com.entidades.buenSabor.repositories.ArticuloRepository;
import com.entidades.buenSabor.repositories.BaseRepository;
import com.entidades.buenSabor.repositories.CategoriaRepository;
import com.entidades.buenSabor.repositories.SucursalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoriaServiceImp extends BaseServiceImp<Categoria, Long> implements CategoriaService {

    @Autowired
    SucursalService sucursalService;
    @Autowired
    SucursalRepository sucursalRepository;
    @Autowired
    CategoriaRepository categoriaRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public Categoria update(Categoria request, Long id) {
        Categoria categoriaOld = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));
        Set<Sucursal> sucursalesOld = new HashSet<>(categoriaOld.getSucursales());

        // Guardar la entidad actualizada
        Categoria entitySaved = categoriaRepository.save(request);

        // Obtener las sucursales de la categoría actualizada
        Set<Sucursal> sucursalesNew = request.getSucursales();

        // Eliminar la categoría antigua de las sucursales que ya no la tienen
        for (Sucursal sucursal : sucursalesOld) {
            if (!sucursalesNew.contains(sucursal)) {
                sucursal.getCategorias().remove(categoriaOld);
                sucursalService.create(sucursal); // Suponiendo que sucursalService tiene un método save para guardar sucursales
            }
        }

        // Añadir la categoría actualizada a las nuevas sucursales
        for (Sucursal sucursal : sucursalesNew) {
            if (!sucursal.getCategorias().contains(entitySaved)) {
                sucursal.getCategorias().add(entitySaved);
                sucursalService.create(sucursal); // Suponiendo que sucursalService tiene un método save para guardar sucursales
            }
        }

        return entitySaved;
    }



    @Override
    public Categoria addSubCategoria(Long idCategoria, Categoria subCategoriaToCreate) {
        var categoria = baseRepository.getById(idCategoria);
        create(subCategoriaToCreate);
        categoria.getSubCategorias().add(subCategoriaToCreate);
        return baseRepository.save(categoria);
    }

    @Override
    public List<ArticuloInsumo> getInsumoByCategoriaId(Long idCategoria){
        return categoriaRepository.findAllByCategoriaId(idCategoria);
    }

    @Override
    public List<ArticuloManufacturado> getManufacturadoByCategoriaId(Long idCategoria){
        return categoriaRepository.findManufacturadosByCategoriaId(idCategoria);
    }

    public List<Articulo> getArticulosByCategoriaId(Long idCategoria){
        List<Articulo> articulos = new ArrayList<>();;
        for (ArticuloInsumo insumo: categoriaRepository.findAllByCategoriaId(idCategoria)) {
            articulos.add(insumo);
        }
        for (ArticuloManufacturado manufacturado: categoriaRepository.findManufacturadosByCategoriaId(idCategoria)) {
            articulos.add(manufacturado);
        }
        return articulos;
    }

    @Override
    public List<Categoria> getAllCategoriasVenta(){
        return categoriaRepository.findCategoriasParaVender();
    }

}
