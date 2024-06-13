package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido, Long> {
    @Query(nativeQuery = true, value = "SELECT fecha_pedido AS Fecha, id as 'Numero Pedido', total AS 'Total Venta', " +
            "CASE forma_pago WHEN 0 THEN 'EFECTIVO' WHEN 1 THEN 'MERCADO PAGO' END AS 'Forma de Pago' FROM Pedido p " +
            "WHERE fecha_pedido = :dia ORDER BY id")
    List<Pedido> ingresosDiarios(@Param("dia") LocalDate dia);

    @Query(nativeQuery = true, value = "SELECT fecha_pedido AS Fecha, SUM(total) AS Recaudacion FROM Pedido p WHERE " +
            "EXTRACT(MONTH FROM fecha_pedido) = :mes GROUP BY fecha_pedido ORDER BY fecha_pedido")
    List<Object[]> ingresosMensuales(@Param("mes") int mes);

    @Query(nativeQuery = true, value = "SELECT fecha_pedido AS Fecha, SUM(total) AS Ingresos, SUM(total_costo) AS " +
            "Costos, SUM(total - total_costo) AS 'Ganancia Total' FROM Pedido p WHERE fecha_pedido BETWEEN " +
            ":fechaDesde AND :fechaHasta GROUP BY fecha_pedido ORDER BY fecha_pedido")
    List<Object[]> ganancias(@Param("fechaDesde") LocalDate fechaDesde, @Param("fechaHasta") LocalDate fechaHasta);

    @Query(nativeQuery = true, value = "SELECT c.id AS 'Nro Cliente', CONCAT(c.nombre, ' ', c.apellido) AS Cliente, " +
            "COUNT(p.cliente_id) AS 'Cantidad Pedidos', s.nombre AS Sucursal FROM cliente c JOIN pedido p ON p" +
            ".cliente_id = c.id JOIN sucursal s ON p.sucursal_id = s.id  WHERE p.fecha_pedido BETWEEN :fechaDesde AND" +
            " :fechaHasta GROUP BY c.id ORDER BY COUNT(p.cliente_id) DESC;")
    List<Object[]> pedidosPorCliente(@Param("fechaDesde") LocalDate fechaDesde,
                                     @Param("fechaHasta") LocalDate fechaHasta);
}
