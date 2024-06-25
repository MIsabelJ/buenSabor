package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.*;
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

    @Query("SELECT p FROM Promocion p JOIN p.sucursales s WHERE s.id = :sucursalId")
    List<Promocion> getPromocionBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query("SELECT p FROM Pedido p WHERE p.sucursal.id = :sucursalId")
    List<Pedido> getPedidoBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query("SELECT a FROM Articulo a WHERE a.sucursal.id = :sucursalId")
    List<Articulo> getArticuloBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query("SELECT i FROM ArticuloInsumo  i WHERE i.sucursal.id = :sucursalId")
    List<ArticuloInsumo> getInsumoBySucursalId(@Param("sucursalId") Long sucursalId);

    @Query("SELECT m FROM ArticuloManufacturado m WHERE m.sucursal.id = :sucursalId")
    List<ArticuloManufacturado> getManufacturadoBySucursalId(@Param("sucursalId") Long sucursalId);

}
