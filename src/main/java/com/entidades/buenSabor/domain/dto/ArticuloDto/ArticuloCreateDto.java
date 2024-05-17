package com.entidades.buenSabor.domain.dto.ArticuloDto;

import com.entidades.buenSabor.domain.entities.ImagenArticulo;

import java.util.HashSet;
import java.util.Set;

public class ArticuloCreateDto {
    protected String denominacion;
    protected Double precioVenta;
    protected Set<ImagenArticulo> imagenes = new HashSet<>();
}
