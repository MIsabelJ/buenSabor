package com.entidades.buenSabor.domain.dto.ImagenPromocion;

import com.entidades.buenSabor.domain.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ImagenPromocionDto extends BaseDto {
    private String url;
}
