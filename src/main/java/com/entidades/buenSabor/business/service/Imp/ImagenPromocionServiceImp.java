package com.entidades.buenSabor.business.service.Imp;

import com.entidades.buenSabor.business.service.Base.BaseServiceImp;
import com.entidades.buenSabor.business.service.CloudinaryService;
import com.entidades.buenSabor.business.service.ImagenPromocionService;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.ImagenPromocion;
import com.entidades.buenSabor.repositories.ImagenArticuloRepository;
import com.entidades.buenSabor.repositories.ImagenPromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImagenPromocionServiceImp implements ImagenPromocionService {
    @Autowired
    private CloudinaryService cloudinaryService; // Servicio para interactuar con Cloudinary
    @Autowired
    private ImagenPromocionRepository imageRepository; // Repositorio para interactuar con la base de datos de imágenes

    // Método para obtener todas las imágenes almacenadas
    @Override
    public ResponseEntity<List<Map<String, Object>>> getAllImages() {
        try {
            // Consultar todas las imágenes desde la base de datos
            List<ImagenPromocion> images = imageRepository.findAll();
            List<Map<String, Object>> imageList = new ArrayList<>();

            // Convertir cada imagen en un mapa de atributos para devolver como JSON
            for (ImagenPromocion image : images) {
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

    // Método para subir imágenes a Cloudinary y guardar los detalles en la base de datos
    @PostMapping("/uploadImages")
    public List<ImagenPromocion> uploadImages(@RequestParam("files") MultipartFile[] files) {
        List<ImagenPromocion> savedImages = new ArrayList<>();

        try {
            // Iterar sobre cada archivo recibido
            for (MultipartFile file : files) {
                // Verificar si el archivo está vacío
                if (file.isEmpty()) {
                    return null;
                }

                // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
                ImagenPromocion image = new ImagenPromocion();
                image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
                image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

                // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
                if (image.getUrl() == null) {
                    return null;
                }

                // Guardar la entidad Image en la base de datos
                //ImagenPromocion savedImage = imageRepository.save(image);

                // Agregar la imagen guardada a la lista de imágenes subidas
                savedImages.add(image);
            }

            // Devolver la lista de imágenes guardadas como JSON con estado OK (200)
            return savedImages;

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso de subida
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
    public Set<ImagenPromocion> findByIds(Set<String> ids) {
        Set<UUID> idsUUID = ids.stream().map(UUID::fromString).collect(Collectors.toSet());
        return idsUUID.stream()
                .map(id -> imageRepository.findById(id).orElse(null))
                .collect(Collectors.toSet());
    }
}
