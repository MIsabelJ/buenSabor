package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.PedidoRepository;
import com.entidades.buenSabor.utils.reports.ExcelManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/articulo-manufacturado")
@CrossOrigin("*")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado,
        ArticuloManufacturadoDto, ArticuloManufacturadoPostDto, ArticuloManufacturadoPostDto, Long,
        ArticuloManufacturadoFacadeImp> {

    @Autowired
    private ExcelManager excelManager;
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }

    // CHARTS
    @GetMapping("/dataMasPedidos")
    public List<List<Object>> getDataMasPedidos(@RequestParam("fechaDesde") @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaDesde, @RequestParam("fechaHasta") @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Cantidad Vendida", "Comida"));

        List<Object[]> resultados = articuloManufacturadoRepository.obtenerComidasMasPedidas(fechaDesde,
                fechaHasta);

        for (Object[] manufacturado : resultados) {
            String comida = (String) manufacturado[0];
            Long cantidadVendida = ((Number) manufacturado[1]).longValue();

            data.add(Arrays.asList(cantidadVendida, comida));
        }

        return data;
    }

    // EXCEL
    @GetMapping("/downloadExcelMasVendidos")
    public ResponseEntity<byte[]> downloadExcelMasVendidos(@RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaDesde, @RequestParam @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelMasVendidos(fechaDesde, fechaHasta);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument" +
                    ".spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "comidas_mas_vendidas.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
