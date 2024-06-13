package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PedidoFacadeImp;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMp;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.repositories.PedidoRepository;
import com.entidades.buenSabor.utils.reports.ExcelManager;
import com.entidades.buenSabor.utils.reports.PdfManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController extends BaseControllerImp<Pedido, PedidoDto, PedidoPostDto, PedidoPostDto, Long,
        PedidoFacadeImp> {

    @Autowired
    private ExcelManager excelManager;
    @Autowired
    private PdfManager pdfManager;
    @Autowired
    private PedidoRepository pedidoRepository;
    private final MercadoPagoController mercadoPagoController;

    public PedidoController(PedidoFacadeImp facade, MercadoPagoController mercadoPagoController) {

        super(facade);
        this.mercadoPagoController = mercadoPagoController;
    }

    // CHARTS
    @GetMapping("/dataIngresosMensuales")
    public List<List<Object>> getIngresosMensuales(@RequestParam("mes") int mes) {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Fecha", "Recaudacion"));

        // Consulta a la base de datos para obtener la cantidad de pedidos agrupados por mes
        List<Object[]> resultados = pedidoRepository.ingresosMensuales(mes);

        for (Object[] resultado : resultados) {
            Date fecha = (Date) resultado[0];
            LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            BigDecimal recaudacion = (BigDecimal) resultado[1];

            String fechaFormateada = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            Double recaudacionDouble = recaudacion.doubleValue();

            data.add(Arrays.asList(fechaFormateada, recaudacionDouble));
        }

        return data;
    }

    //MERCADO PAGO
    @PostMapping("/create_preference_mp")
    public PreferenceMp crearPreferenceMp(@RequestBody PedidoPostDto pedido){
        PreferenceMp preferenceMp = mercadoPagoController.getPreferenciaIdMercadoPago(pedido);
        return preferenceMp;
    }

    @GetMapping("/dataGanancias")
    public List<List<Object>> getDataGanancias(
            @RequestParam("fechaDesde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Fecha", "Ingresos", "Costos", "Ganancias"));

        List<Object[]> resultados = pedidoRepository.ganancias(fechaDesde, fechaHasta);

        for (Object[] resultado : resultados) {
            LocalDate fecha = ((Date) resultado[0]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String fechaFormateada = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);

            BigDecimal ingresos = (BigDecimal) resultado[1];
            BigDecimal costos = (BigDecimal) resultado[2];
            BigDecimal ganancias = (BigDecimal) resultado[3];

            data.add(Arrays.asList(
                    fechaFormateada,
                    ingresos.doubleValue(),
                    costos.doubleValue(),
                    ganancias.doubleValue()
            ));
        }

        return data;
    }

    @GetMapping("/dataPedidosPorCliente")
    public List<List<Object>> getDataPedidosPorCliente(@RequestParam("fechaDesde") @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaDesde, @RequestParam("fechaHasta") @DateTimeFormat(iso =
            DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Cantidad Pedidos", "Cliente"));

        List<Object[]> resultados = pedidoRepository.pedidosPorCliente(fechaDesde, fechaHasta);

        for (Object[] resultado : resultados) {
            String nombreCliente = (String) resultado[1];
            Long cantidadPedidos = (Long) resultado[2];

            data.add(Arrays.asList(nombreCliente, cantidadPedidos));
        }

        return data;
    }

    // EXCEL
    @GetMapping("/downloadExcelIngresosDiarios")
    public ResponseEntity<byte[]> downloadExcelIngresosDiarios(@RequestParam LocalDate dia) {
        try {
            ByteArrayOutputStream outputStream = excelManager.excelIngresosDiarios(dia);

            String filename = "ingresos_diarios-" + dia.toString() + ".xlsx";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument" +
                    ".spreadsheetml.sheet"));
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
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument" +
                    ".spreadsheetml.sheet"));
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
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument" +
                    ".spreadsheetml.sheet"));
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
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument" +
                    ".spreadsheetml.sheet"));
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
