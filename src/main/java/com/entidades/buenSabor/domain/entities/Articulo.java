package com.entidades.buenSabor.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@Audited
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArticuloInsumo.class, name = "ArticuloInsumo"),
        @JsonSubTypes.Type(value = ArticuloManufacturado.class, name = "ArticuloManufacturado")
})
public class Articulo  extends Base {

    private Double precioCompra;
    protected String denominacion;
    protected Double precioVenta;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    //SE AGREGA EL JOIN COLUMN PARA QUE JPA NO CREE LA TABLA INTERMEDIA EN UNA RELACION ONE TO MANY
    //DE ESTA MANERA PONE EL FOREIGN KEY 'cliente_id' EN LA TABLA DE LOS MANY
    @JoinColumn(name = "articulo_id")
    //SE AGREGA EL BUILDER.DEFAULT PARA QUE BUILDER NO SOBREESCRIBA LA INICIALIZACION DE LA LISTA
    @Builder.Default
    @NotAudited
    protected List<ImagenArticulo> imagenes = new ArrayList<>();

    @ManyToOne
    protected UnidadMedida unidadMedida;

    @ManyToOne
    protected Sucursal sucursal;
}

