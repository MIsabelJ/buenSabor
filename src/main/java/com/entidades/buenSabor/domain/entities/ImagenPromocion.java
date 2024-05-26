package com.entidades.buenSabor.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity // Anotación que marca la clase como una entidad JPA
@Data // Anotación de Lombok para la generación automática de getters, setters, toString, etc.
@Table(name = "imagen_promocion") // Especifica el nombre de la tabla en la base de datos
public class ImagenPromocion{
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.UUID) // Generación automática del UUID como estrategia de generación
    @Column(name = "id") // Mapea este campo a la columna "id" en la tabla
    private UUID id; // Identificador único de la imagen

    @Column(name = "name_image") // Mapea este campo a la columna "name_image" en la tabla
    private String name; // Nombre de la imagen

    @Column(name = "url_image") // Mapea este campo a la columna "url_image" en la tabla
    private String url; // URL de la imagen en almacenamiento externo (por ejemplo, Cloudinary)
}
