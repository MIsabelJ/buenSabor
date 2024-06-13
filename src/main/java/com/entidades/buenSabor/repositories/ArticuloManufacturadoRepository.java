package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado, Long> {
    @Query(nativeQuery = true, value = "CALL comidas_mas_pedidas(:fechaDesde, :fechaHasta)")
    List<ArticuloManufacturado> obtenerComidasMasPedidas(@Param("fechaDesde") LocalDate fechaDesde, @Param(
            "fechaHasta") LocalDate fechaHasta);
}
