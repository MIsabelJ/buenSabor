package com.entidades.buenSabor.business.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CloudinaryService {

    // Método para subir un archivo a Cloudinary
    public default String uploadFile(MultipartFile file) {
        return null; // Implementación por defecto que retorna null
    }

    // Método para eliminar una imagen de Cloudinary
    public ResponseEntity<String> deleteImage(String publicId, UUID uuid);

}
