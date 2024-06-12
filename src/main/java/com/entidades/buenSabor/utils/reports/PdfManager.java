package com.entidades.buenSabor.utils.reports;

import com.entidades.buenSabor.business.service.FacturaService;
import com.entidades.buenSabor.business.service.PedidoService;
import com.entidades.buenSabor.domain.entities.Factura;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.domain.enums.FormaPago;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

@Component
public class PdfManager {

    @Autowired
    PedidoService pedidoService;

    protected static Font titulo = new Font(Font.COURIER, 14, Font.BOLD);
    protected static Font redFont = new Font(Font.COURIER, 12, Font.NORMAL, Color.RED);
    protected static Font textoHeader = new Font(Font.COURIER, 17, Font.BOLD);
    protected static Font texto = new Font(Font.COURIER, 11, Font.NORMAL);
    protected static Font textoBold = new Font(Font.COURIER, 11, Font.BOLD);
    protected static Font textoMini = new Font(Font.COURIER, 8, Font.NORMAL);
    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);

    public static void addEmptyLine(Document document, int number) {
        try {
            Paragraph espacio = new Paragraph();
            for (int i = 0; i < number; i++) {
                espacio.add(new Paragraph(" "));
            }
            document.add(espacio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException {
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
        cellOne.setBorder(Rectangle.BOTTOM);
        cellOne.setBorder(Rectangle.TOP);
        linea.addCell(cellOne);

        document.add(linea);
    }

    public ResponseEntity<byte[]> downloadFactura(Long pedidoId, ByteArrayOutputStream outputStream) {
        try {
            Document document = new Document(PageSize.A4, 30, 30, 30, 30);

            Pedido pedido = pedidoService.getById(pedidoId);

            PdfWriter.getInstance(document, outputStream);
            document.open();

            Paragraph paragraph = new Paragraph("Factura n° " + pedido.getFactura().getId().toString(), titulo);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);

            addEmptyLine(document, 2);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            PdfPCell celdaIzq = new PdfPCell(new Phrase("Fecha de facturación:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            PdfPCell celdaDer = new PdfPCell(new Phrase(pedido.getFactura().getFechaFacturacion().toString(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Cliente:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer =
                    new PdfPCell(new Phrase(pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido()
                            , texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Total de la venta:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(pedido.getFactura().getTotalVenta().toString(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            String formaPagoTexto = "";
            switch (pedido.getFormaPago()) {
                case EFECTIVO:
                    formaPagoTexto = "Efectivo";
                    break;
                case MERCADO_PAGO:
                    formaPagoTexto = "Mercado Pago";
                    break;
            }

            celdaIzq = new PdfPCell(new Phrase("Forma de pago:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(formaPagoTexto, texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            String tipoEnvioTexto = "";
            switch (pedido.getTipoEnvio()) {
                case DELIVERY:
                    tipoEnvioTexto = "Delivery";
                    break;
                case TAKE_AWAY:
                    tipoEnvioTexto = "Take away";
                    break;
            }

            celdaIzq = new PdfPCell(new Phrase("Tipo de envío:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(tipoEnvioTexto, texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("ID empleado responsable:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(pedido.getEmpleado().getId().toString(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            document.add(table);

            document.close();

            String filename = "factura_pedido_" + pedidoId + ".pdf";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
