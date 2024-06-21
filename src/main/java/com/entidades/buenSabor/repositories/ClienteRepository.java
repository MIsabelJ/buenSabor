package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente,Long> {

    @Query("SELECT c FROM Cliente c WHERE c.usuarioCliente.id = :id")
    public Cliente findByUserId(Long id);
}
