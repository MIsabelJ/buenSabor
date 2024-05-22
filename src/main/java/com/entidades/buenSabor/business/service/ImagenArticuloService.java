package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.business.service.Base.BaseService;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import org.mapstruct.Named;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ImagenArticuloService  {
    // Método para obtener todas las imágenes almacenadas
    ResponseEntity<List<Map<String, Object>>> getAllImages();

    // Método para subir imágenes al sistema
    ResponseEntity<String> uploadImages(MultipartFile[] files);

    // Método para eliminar una imagen por su identificador público y UUID
    ResponseEntity<String> deleteImage(String publicId, UUID uuid);

    @Named("findByUUID")
    public Set<ImagenArticulo> findByIds(Set<UUID> ids);
}
