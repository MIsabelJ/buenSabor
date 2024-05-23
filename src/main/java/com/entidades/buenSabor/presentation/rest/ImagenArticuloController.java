package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ImagenArticuloFacadeImp;
import com.entidades.buenSabor.business.service.ImagenArticuloService;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloPostDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/imagen-articulo")
@CrossOrigin("*")
public class ImagenArticuloController  {
    @Autowired
    private ImagenArticuloService imageService; // Inyección de dependencia del servicio ImageService

    // Método POST para subir imágenes
    @PostMapping("/uploads")
    public ResponseEntity<?> uploadImages(
            @RequestParam(value = "uploads", required = true) MultipartFile[] files) {
        try {
            return imageService.uploadImages(files); // Llama al método del servicio para subir imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    // Método POST para eliminar imágenes por su publicId y UUID
    @PostMapping("/deleteImg")
    public ResponseEntity<String> deleteById(
            @RequestParam(value = "publicId", required = true) String publicId,
            @RequestParam(value = "uuid", required = true) String uuidString) {
        try {
            UUID uuid = UUID.fromString(uuidString); // Convierte la cadena UUID en un objeto UUID
            return imageService.deleteImage(publicId, uuid); // Llama al método del servicio para eliminar la imagen
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Invalid UUID format"); // Respuesta HTTP 400 si el UUID no es válido
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An error occurred"); // Respuesta HTTP 500 si ocurre un error inesperado
        }
    }

    // Método GET para obtener todas las imágenes almacenadas
    @GetMapping("/getImages")
    public ResponseEntity<List<Map<String, Object>>> getAll() {
        try {
            return imageService.getAllImages(); // Llama al método del servicio para obtener todas las imágenes
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo básico de errores, se puede mejorar para devolver una respuesta más específica
        }
    }

    @GetMapping("/getAllImagesById")
    public ResponseEntity<?> getAllById(@RequestParam("uuid") Set<String> uuids){
        try{
            Set<ImagenArticulo> images = imageService.findByIds(uuids);
            if (images.isEmpty()){
                return  ResponseEntity.status(404).body("{\"status\":\"ERROR\", \"message\":\"No images found for the provided IDs\"}");
            }
            return ResponseEntity.ok(images);
        }catch (Exception e){
            e.printStackTrace();
            // Devolver un error (400) si ocurre alguna excepción durante el proceso
            return ResponseEntity.status(400).body("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }

}
