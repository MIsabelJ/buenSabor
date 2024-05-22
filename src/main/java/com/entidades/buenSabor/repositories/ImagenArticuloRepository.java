package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenArticuloRepository extends JpaRepository<ImagenArticulo, UUID> {
}
