package com.entidades.buenSabor.domain.dto.ImagenPromocion;

import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImagenPromocionDto {
    private String nombre;
    private MultipartFile file;
}
