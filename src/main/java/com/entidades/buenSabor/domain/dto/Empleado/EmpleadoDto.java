package com.entidades.buenSabor.domain.dto.Empleado;

import com.entidades.buenSabor.domain.dto.BaseDto;
import com.entidades.buenSabor.domain.entities.ImagenArticulo;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmpleadoDto extends BaseDto {
    private Rol tipoEmpleado;
    private Set<Pedido> pedidos;
    private Long idSucursal;
//    protected List<ImagenArticulo> imagenes;
}
