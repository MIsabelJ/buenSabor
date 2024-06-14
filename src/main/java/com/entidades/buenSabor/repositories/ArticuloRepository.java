package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.Articulo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloRepository  extends BaseRepository<Articulo, Long> {
    @Query("SELECT a FROM Articulo a WHERE a.precioVenta != 0")
    public List<Articulo> getAllArticulosVenta();
}
