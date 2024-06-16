package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends BaseRepository<Empleado,Long> {
    @Query("SELECT e FROM Empleado e WHERE e.usuarioEmpleado.email = :email")
    Empleado findByEmail(@Param("email") String email);
}
