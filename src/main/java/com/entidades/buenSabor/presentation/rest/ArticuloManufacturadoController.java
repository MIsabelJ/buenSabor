package com.entidades.buenSabor.presentation.rest;

import com.entidades.buenSabor.business.facade.Imp.ArticuloManufacturadoFacadeImp;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoDto;
import com.entidades.buenSabor.domain.dto.ArticuloManufacturado.ArticuloManufacturadoPostDto;
import com.entidades.buenSabor.domain.entities.ArticuloManufacturado;
import com.entidades.buenSabor.presentation.rest.Base.BaseControllerImp;
import com.entidades.buenSabor.utils.reports.ExcelManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping("/articulo-manufacturado")
@CrossOrigin("*")
public class ArticuloManufacturadoController extends BaseControllerImp<ArticuloManufacturado,
        ArticuloManufacturadoDto, ArticuloManufacturadoPostDto, ArticuloManufacturadoPostDto, Long,
        ArticuloManufacturadoFacadeImp> {

    private ExcelManager excelManager;

    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImp facade) {
        super(facade);
    }

    // EXCEL
    @GetMapping("/downloadExcelMasVendidos")
    public ResponseEntity<byte[]> downloadExcelMasVendidos(@RequestParam LocalDate fechaDesde,
                                                           @RequestParam LocalDate fechaHasta) {
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
