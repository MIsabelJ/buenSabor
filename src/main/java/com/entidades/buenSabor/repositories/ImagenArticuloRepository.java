package com.entidades.buenSabor.repositories;

import com.entidades.buenSabor.domain.entities.Empresa;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImagenArticuloRepository extends JpaRepository<ImagenArticulo, UUID> {


}
