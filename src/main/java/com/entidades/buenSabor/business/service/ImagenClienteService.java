package com.entidades.buenSabor.business.service;

import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import org.mapstruct.Named;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface ImagenClienteService {
    ResponseEntity<List<Map<String, Object>>> getAllImages();

    // Método para subir imágenes al sistema
    @Named("uploadImages")
    ResponseEntity<ImagenCliente> uploadImage(MultipartFile file);

    // Método para eliminar una imagen por su identificador público y UUID
    ResponseEntity<String> deleteImage(String publicId, UUID uuid);

    @Named("findByIds")
    public Set<ImagenCliente> findByIds(Set<String> ids);
}
