package com.entidades.buenSabor.domain.dto.ArticuloInsumo;

import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloDto;
import com.entidades.buenSabor.domain.dto.ImagenArticulo.ImagenArticuloPostDto;
import com.entidades.buenSabor.domain.dto.UnidadMedida.UnidadMedidaDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ArticuloInsumoPostDto {
    protected String denominacion;
    protected Double precioVenta;
    protected List<ImagenArticulo> imagenes;
    private Double precioCompra;
    private Integer stockActual;
    private Integer stockMaximo;
    private Integer stockMinimo;
    private Boolean esParaElaborar;
    protected Long idUnidadMedida;
    private Long idCategoria;
    private Long idSucursal;
}
