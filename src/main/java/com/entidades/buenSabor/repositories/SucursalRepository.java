package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Categoria;
import com.entidades.buenSabor.domain.entities.Sucursal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface SucursalRepository extends BaseRepository<Sucursal,Long> {
    @Query("SELECT s FROM Sucursal s LEFT JOIN FETCH s.promociones WHERE s.id = :id")
    Sucursal findWithPromocionesById(@Param("id") Long id);

    @Query("SELECT c FROM Categoria c JOIN c.sucursales s WHERE s.id = :sucursalId")
    List<Categoria> getCategoriasBySucursalId(@Param("sucursalId") Long sucursalId);

}
