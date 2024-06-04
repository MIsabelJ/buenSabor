package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.ImagenClienteService;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.ImagenCliente;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.ImagenClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImagenCienteServiceImp implements ImagenClienteService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenClienteRepository imageRepository; // Repositorio para interactuar con la base de datos de imágenes

    // Método para obtener todas las imágenes almacenadas
    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImages() {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenCliente> images = imageRepository.findAll();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenCliente image : images) {
                Map<String, Object> imageMap = new HashMap<>();
                imageMap.put("id", image.getId());
                imageMap.put("name", image.getName());
                imageMap.put("url", image.getUrl());
                imageList.add(imageMap);
            }

            // Devolver la lista de imágenes como ResponseEntity con estado OK (200)
            return ResponseEntity.ok(imageList);
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error interno del servidor (500) si ocurre alguna excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Método para subir imágenes a Cloudinary y retornar la lista de imagenes subidas
    @Override
    public ResponseEntity<ImagenCliente> uploadImage(@RequestParam("files") MultipartFile file) {
        ImagenCliente imagenCliente = new ImagenCliente();
        try {
            if (file.isEmpty()) {
                return null;
            }
            imagenCliente.setName(file.getOriginalFilename());
            imagenCliente.setUrl(cloudinaryService.uploadFile(file));
            if (imagenCliente.getUrl() == null) {
                return null;
            }
            return ResponseEntity.ok(imagenCliente);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para eliminar una imagen por su identificador en la base de datos y en Cloudinary
    @Override
    public ResponseEntity<String> deleteImage(String publicId, UUID idBd) {
        try {
            // Eliminar la imagen de la base de datos usando su identificador
            imageRepository.deleteById(idBd);

            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
            return cloudinaryService.deleteImage(publicId, idBd);

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Set<ImagenCliente> findByIds(Set<String> ids) {
        Set<UUID> idsUUID = ids.stream().map(UUID::fromString).collect(Collectors.toSet());
        return idsUUID.stream()
                .map(id -> imageRepository.findById(id).orElse(null))
                .collect(Collectors.toSet());
    }
}
