package com.entidades.buenSabor.domain.dto.ArticuloManufacturado;

import com.entidades.buenSabor.domain.dto.ArticuloManufacturdadoDetalle.ArticuloManufacturadoDetallePostDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;

import java.util.List;
import java.util.Set;

public class ArticuloManufacturadoEditDto {
    protected String denominacion;
    protected Double precioVenta;
    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;
    private Set<ArticuloManufacturadoDetallePostDto> articuloManufacturadoDetalles;
    protected List<ImagenArticulo> imagenes;
    protected Long idUnidadMedida;
    private Long idCategoria;
    private Set<Long> idArticuloManufacturadoDetalle;
}
