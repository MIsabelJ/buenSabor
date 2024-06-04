package com.entidades.buenSabor.repositories;


import com.entidades.buenSabor.domain.entities.ImagenCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenClienteRepository extends JpaRepository<ImagenCliente, UUID> {
}
