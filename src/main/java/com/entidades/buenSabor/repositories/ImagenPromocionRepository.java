package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.ImagenPromocion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ImagenPromocionRepository extends JpaRepository<ImagenPromocion, UUID> {
}
