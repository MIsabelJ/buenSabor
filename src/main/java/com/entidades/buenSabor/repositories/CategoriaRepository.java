package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.Categoria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria,Long>{

    @Query("SELECT a FROM ArticuloInsumo a WHERE a.categoria.id =:idCategoria ")
    List<ArticuloInsumo> findAllByCategoriaId(@Param("idCategoria") Long idCategoria);

    @Query("SELECT a FROM ArticuloManufacturado a WHERE a.categoria.id =:idCategoria")
    List<ArticuloManufacturado> findManufacturadosByCategoriaId(@Param("idCategoria") Long idCategoria);
}
