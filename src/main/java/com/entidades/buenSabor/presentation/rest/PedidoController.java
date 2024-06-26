package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.PedidoFacadeImp;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoDto;
import com.entidades.buenSabor.domain.dto.Pedido.PedidoPostDto;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.entities.PreferenceMp;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.repositories.PedidoRepository;
import com.entidades.buenSabor.utils.reports.ExcelManager;
import com.entidades.buenSabor.utils.reports.PdfManager;
import com.entidades.buenSabor.utils.reports.email.EmailManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
    private EmailManager emailService;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoService pedidoService;

    private final MercadoPagoController mercadoPagoController;

    public PedidoController(PedidoFacadeImp facade, MercadoPagoController mercadoPagoController) {
        super(facade);
        this.mercadoPagoController = mercadoPagoController;
    }

    //MERCADO PAGO
    @PostMapping("/create_preference_mp")
    public PreferenceMp crearPreferenceMp(@RequestBody PedidoPostDto pedido){
        PreferenceMp preferenceMp = mercadoPagoController.getPreferenciaIdMercadoPago(pedido);
        return preferenceMp;
    }

    // CHARTS
    @GetMapping("/dataIngresosMensuales")
    public List<List<Object>> getIngresosMensuales(@RequestParam("mes") int mes) {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Fecha", "Recaudacion"));

        // Consulta a la base de datos para obtener la cantidad de pedidos agrupados por mes
        List<Object[]> resultados = pedidoRepository.ingresosMensuales(mes);

        for (Object[] resultado : resultados) {
            // Asumimos que el primer elemento es de tipo java.sql.Date
            java.sql.Date sqlDate = (java.sql.Date) resultado[0];
            LocalDate fecha = sqlDate.toLocalDate(); // Convertimos java.sql.Date a LocalDate
            String fechaFormateada = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);

            Double recaudacion = (Double) resultado[1];

            data.add(Arrays.asList(fechaFormateada, recaudacion));
        }

        return data;
    }

    @GetMapping("/dataGanancias")
    public List<List<Object>> getDataGanancias(
            @RequestParam("fechaDesde") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam("fechaHasta") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {

        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Fecha", "Ingresos", "Costos", "Ganancias"));

        List<Object[]> resultados = pedidoRepository.ganancias(fechaDesde, fechaHasta);

        for (Object[] resultado : resultados) {
            // Asumimos que el primer elemento es de tipo java.sql.Date
            java.sql.Date sqlDate = (java.sql.Date) resultado[0];
            LocalDate fecha = sqlDate.toLocalDate(); // Convertimos java.sql.Date a LocalDate
            String fechaFormateada = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);

            Double ingresos = (Double) resultado[1];
            Double costos = (Double) resultado[2];
            Double ganancias = (Double) resultado[3];

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
        try {
            byte[] pdfBytes = pdfManager.downloadFactura(id);

            String filename = "factura_pedido_" + id + ".pdf";

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment().filename(filename).build());
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //EMAIL
    @PostMapping("/sendFacturaPedido/{id}")
    public ResponseEntity<String> sendFacturaPedido(@PathVariable Long id) {
        try {
            Pedido pedido = pedidoService.getById(id);
            byte[] pdfBytes = pdfManager.downloadFactura(id);

            String to = pedido.getCliente().getUsuarioCliente().getEmail();
            String subject = "Factura de su pedido #" + id;
            String body = "Estimado cliente,\n\nAdjunto encontrará la factura de su pedido.";
            String filename = "factura_pedido_" + id + ".pdf";

            emailService.sendEmailWithAttachment(to, subject, body, pdfBytes, filename);

            return ResponseEntity.ok("Factura enviada por correo electrónico");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al enviar la factura");
        }
    }

    @PutMapping("/{id}/{reponer}")
    public ResponseEntity<PedidoDto> upDatePedido(@PathVariable Long id, @PathVariable boolean reponer) {
        return ResponseEntity.ok(facade.cancelPedido(id, reponer));
    }


}
