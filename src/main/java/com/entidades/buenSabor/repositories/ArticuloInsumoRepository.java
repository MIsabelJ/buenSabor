package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {

    @Query("SELECT a FROM ArticuloInsumo a WHERE a.precioVenta != 0")
    public List<ArticuloInsumo> findAllVenta();
}
