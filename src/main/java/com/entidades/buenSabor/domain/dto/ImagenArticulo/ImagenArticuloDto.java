package com.entidades.buenSabor.domain.dto.ImagenArticulo;

import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImagenArticuloDto extends BaseDto {
    private String url;
}
