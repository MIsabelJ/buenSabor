package com.entidades.buenSabor.domain.dto.Articulo;

import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloPostDto {
    private String denominacion;
    private Double precioVenta;
    private Double precioCompra;
    private List<ImagenArticulo> imagenes;
    private Long idUnidadMedida;
    private Long idSucursal;
}
