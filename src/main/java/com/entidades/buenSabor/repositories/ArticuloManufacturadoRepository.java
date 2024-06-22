package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Articulo;
import com.entidades.buenSabor.domain.entities.ArticuloInsumo;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {
    @Query(nativeQuery = true, value = "SELECT a.denominacion AS Comida, SUM(dp.cantidad) AS \"Cantidad Vendida\", c" +
            ".denominacion AS Categoria, s.nombre AS Sucursal FROM pedido p JOIN detalle_pedido dp ON p.id = dp" +
            ".pedido_id JOIN sucursal s ON p.sucursal_id = s.id JOIN articulo a ON dp.articulo_id = a.id JOIN " +
            "articulo_manufacturado am ON a.id = am.id JOIN categoria c ON am.categoria_id = c.id WHERE p" +
            ".fecha_pedido BETWEEN :fechaDesde AND :fechaHasta GROUP BY am.id, a.denominacion, c.denominacion, s" +
            ".nombre ORDER BY SUM(dp.cantidad) DESC LIMIT 15")
    List<Object[]> obtenerComidasMasPedidas(@Param("fechaDesde") LocalDate fechaDesde, @Param(
            "fechaHasta") LocalDate fechaHasta);

    @Query("SELECT a FROM ArticuloManufacturado a WHERE a.precioVenta != 0")
    List<ArticuloManufacturado> findAllVenta();
}
