package com.entidades.buenSabor.utils.reports;

import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.domain.entities.DetallePedido;
import com.entidades.buenSabor.domain.entities.Pedido;
import com.entidades.buenSabor.repositories.ArticuloManufacturadoRepository;
import com.entidades.buenSabor.repositories.PedidoRepository;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class ExcelManager {

    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private void crearCeldaEncabezado(SXSSFRow fila, int columna, String valor, XSSFCellStyle style) {
        SXSSFCell celda = fila.createCell(columna);
        celda.setCellValue(valor);
        celda.setCellStyle(style);
    }

    private void crearCelda(SXSSFRow fila, int columna, Object valor) {
        SXSSFCell celda = fila.createCell(columna);
        if (valor instanceof String) {
            celda.setCellValue((String) valor);
        } else if (valor instanceof Double) {
            celda.setCellValue((Double) valor);
        } else if (valor instanceof Integer) {
            celda.setCellValue((Integer) valor);
        }
    }

    // -------------- COMIDAS MÁS VENDIDAS --------------
    public ByteArrayOutputStream excelMasVendidos(LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Tamaño de paginación
        SXSSFSheet hoja = libro.createSheet();

        // Estilos para los encabezados
        XSSFFont fontEncabezado = (XSSFFont) libro.createFont();
        fontEncabezado.setBold(true);
        XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();
        styleEncabezado.setFont(fontEncabezado);

        // Crear fila de encabezados
        SXSSFRow filaEncabezados = hoja.createRow(0);
        int columna = 0;
        crearCeldaEncabezado(filaEncabezados, columna++, "Comida", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Cantidad Vendida", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Categoria", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Sucursal", styleEncabezado);

        int filaActual = 1;
        List<Object[]> resultados = articuloManufacturadoRepository.obtenerComidasMasPedidas(fechaDesde, fechaHasta);
        for (Object[] fila : resultados) {
            SXSSFRow filaExcel = hoja.createRow(filaActual++);
            int columnaFila = 0;
            crearCelda(filaExcel, columnaFila++, fila[0].toString()); // Comida
            crearCelda(filaExcel, columnaFila++, fila[1].toString()); // Cantidad Vendida
            crearCelda(filaExcel, columnaFila++, fila[2].toString()); // Categoria
            crearCelda(filaExcel, columnaFila++, fila[3].toString()); // Sucursal
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        libro.close();
        return outputStream;
    }

    // -------------- INGRESOS DIARIOS --------------
    public ByteArrayOutputStream excelIngresosDiarios(LocalDate dia) throws IOException {
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Tamaño de paginación
        SXSSFSheet hoja = libro.createSheet();

        // Estilos para los encabezados
        XSSFFont fontEncabezado = (XSSFFont) libro.createFont();
        fontEncabezado.setBold(true);
        XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();
        styleEncabezado.setFont(fontEncabezado);

        // Crear fila de encabezados
        SXSSFRow filaEncabezados = hoja.createRow(0);
        int columna = 0;
        crearCeldaEncabezado(filaEncabezados, columna++, "Fecha", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Numero Pedido", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Total Venta", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Forma de Pago", styleEncabezado);


        int filaActual = 1;
        List<Object[]> resultado = pedidoRepository.ingresosDiarios(dia);
        for (Object[] pedido : resultado) {

            SXSSFRow fila = hoja.createRow(filaActual++);
            int columnaFila = 0;
            crearCelda(fila, columnaFila++, pedido[0].toString());
            crearCelda(fila, columnaFila++, pedido[1].toString());
            crearCelda(fila, columnaFila++, pedido[2].toString());
            crearCelda(fila, columnaFila++, pedido[3]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        libro.close();
        return outputStream;
    }

    // -------------- INGRESOS MENSUALES --------------
    public ByteArrayOutputStream excelIngresosMensuales(int mes) throws IOException {
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Tamaño de paginación
        SXSSFSheet hoja = libro.createSheet();

        // Estilos para los encabezados
        XSSFFont fontEncabezado = (XSSFFont) libro.createFont();
        fontEncabezado.setBold(true);
        XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();
        styleEncabezado.setFont(fontEncabezado);

        // Crear fila de encabezados
        SXSSFRow filaEncabezados = hoja.createRow(0);
        int columna = 0;
        crearCeldaEncabezado(filaEncabezados, columna++, "Fecha", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Recaudacion", styleEncabezado);

        int filaActual = 1;
        List<Object[]> resultados = pedidoRepository.ingresosMensuales(mes);
        for (Object[] fila : resultados) {
            SXSSFRow filaExcel = hoja.createRow(filaActual++);
            int columnaFila = 0;
            crearCelda(filaExcel, columnaFila++, fila[0].toString());
            crearCelda(filaExcel, columnaFila++, fila[1].toString());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        libro.close();
        return outputStream;
    }

    // -------------- GANANCIAS --------------
    public ByteArrayOutputStream excelGanancias(LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Tamaño de paginación
        SXSSFSheet hoja = libro.createSheet();

        // Estilos para los encabezados
        XSSFFont fontEncabezado = (XSSFFont) libro.createFont();
        fontEncabezado.setBold(true);
        XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();
        styleEncabezado.setFont(fontEncabezado);

        // Crear fila de encabezados
        SXSSFRow filaEncabezados = hoja.createRow(0);
        int columna = 0;
        crearCeldaEncabezado(filaEncabezados, columna++, "Fecha", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Ingresos", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Costos", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Ganancia Total", styleEncabezado);

        int filaActual = 1;
        List<Object[]> resultados = pedidoRepository.ganancias(fechaDesde, fechaHasta);
        for (Object[] fila : resultados) {
            SXSSFRow filaExcel = hoja.createRow(filaActual++);
            int columnaFila = 0;
            crearCelda(filaExcel, columnaFila++, fila[0].toString());
            crearCelda(filaExcel, columnaFila++, fila[1].toString());
            crearCelda(filaExcel, columnaFila++, fila[2].toString());
            crearCelda(filaExcel, columnaFila++, fila[3].toString());
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        libro.close();
        return outputStream;
    }

    // -------------- CANTIDAD DE PEDIDOS POR CLIENTE --------------
    public ByteArrayOutputStream excelPedidosPorCliente(LocalDate fechaDesde, LocalDate fechaHasta) throws IOException {
        SXSSFWorkbook libro = new SXSSFWorkbook(50); // Tamaño de paginación
        SXSSFSheet hoja = libro.createSheet();

        // Estilos para los encabezados
        XSSFFont fontEncabezado = (XSSFFont) libro.createFont();
        fontEncabezado.setBold(true);
        XSSFCellStyle styleEncabezado = (XSSFCellStyle) libro.createCellStyle();
        styleEncabezado.setFont(fontEncabezado);

        // Crear fila de encabezados
        SXSSFRow filaEncabezados = hoja.createRow(0);
        int columna = 0;
        crearCeldaEncabezado(filaEncabezados, columna++, "Nro Cliente", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Cliente", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Cantidad Pedidos", styleEncabezado);
        crearCeldaEncabezado(filaEncabezados, columna++, "Sucursal", styleEncabezado);

        int filaActual = 1;
        List<Object[]> resultados = pedidoRepository.pedidosPorCliente(fechaDesde, fechaHasta);
        for (Object[] fila : resultados) {
            SXSSFRow filaExcel = hoja.createRow(filaActual++);
            int columnaFila = 0;
            crearCelda(filaExcel, columnaFila++, fila[0].toString());
            crearCelda(filaExcel, columnaFila++, fila[1]);
            crearCelda(filaExcel, columnaFila++, fila[2].toString());
            crearCelda(filaExcel, columnaFila++, fila[3]);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        libro.write(outputStream);
        libro.close();
        return outputStream;
    }

}
