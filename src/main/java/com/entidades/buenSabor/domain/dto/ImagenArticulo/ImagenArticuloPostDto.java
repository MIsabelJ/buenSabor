package com.entidades.buenSabor.domain.dto.ImagenArticulo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImagenArticuloPostDto {
    private MultipartFile imagen;
}
