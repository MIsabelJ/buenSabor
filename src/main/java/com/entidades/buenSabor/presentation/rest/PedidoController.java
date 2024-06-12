package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PedidoFacadeImp;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.utils.reports.ExcelManager;
import com.entidades.buenSabor.utils.reports.PdfManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto, Long, PedidoFacadeImp> {

    private ExcelManager excelManager;
    private PdfManager pdfManager;

    public PedidoController(PedidoFacadeImp facade) {
        super(facade);
    }

    // EXCEL
    @GetMapping("/downloadExcelIngresosDiarios")
    public ResponseEntity<byte[]> downloadExcelIngresosDiarios(@RequestParam LocalDate dia) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelIngresosDiarios(dia);

            String filename = "ingresos_diarios-" + dia.toString() + ".xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadExcelIngresosMensuales")
    public ResponseEntity<byte[]> downloadExcelIngresosMensuales(@RequestParam int mes) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelIngresosMensuales(mes);

            String filename = "ingresos_mensuales-" + mes + ".xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadExcelGanancias")
    public ResponseEntity<byte[]> downloadExcelGanancias(@RequestParam LocalDate fechaDesde,
                                                         @RequestParam LocalDate fechaHasta) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelGanancias(fechaDesde, fechaHasta);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "reporte_ganancias.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/downloadExcelPedidosPorCliente")
    public ResponseEntity<byte[]> downloadExcelPedidosPorCliente(@RequestParam LocalDate fechaDesde,
                                                                 @RequestParam LocalDate fechaHasta) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelPedidosPorCliente(fechaDesde, fechaHasta);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "pedidos_por_cliente.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PDF
    @GetMapping("/downloadFacturaPedido/{id}")
    public ResponseEntity<byte[]> downloadFacturaPedido(@PathVariable Long id) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            pdfManager.downloadFactura(id, outputStream);

            String filename = "factura_pedido_" + id + ".pdf";

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
