package iis.iis.controller;

import iis.iis.entity.ReportRequest;
import iis.iis.service.PdfServiceFinance;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

@RestController
@RequestMapping("/pdf")
public class PdfFinanceController {

    private final PdfServiceFinance pdfServiceFinance;

    public PdfFinanceController(PdfServiceFinance pdfServiceFinance) {
        this.pdfServiceFinance = pdfServiceFinance;
    }

    @GetMapping("/report")
    public ResponseEntity<byte[]> getPdfReport() {
        byte[] pdfBytes = pdfServiceFinance.generateNetFlowsPdf(2021, 2024);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/report2")
    public ResponseEntity<byte[]> getPdfReport2(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        // Assuming pdfServiceFinance is your service class to generate PDF bytes
        byte[] pdfBytes = pdfServiceFinance.generatePdf2(startDate, endDate);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/report3")
    public ResponseEntity<byte[]> getPdfReport3(
            @RequestParam int startYear,
            @RequestParam int endYear) {
        // Assuming pdfServiceFinance is your service class to generate PDF bytes
        byte[] pdfBytes = pdfServiceFinance.generatePdf3(startYear, endYear);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }


    @GetMapping("/report4")
    public ResponseEntity<byte[]> getPdfReport4(
            @RequestParam int startYear,
            @RequestParam int endYear) {
        // Assuming pdfServiceFinance is your service class to generate PDF bytes
        byte[] pdfBytes = pdfServiceFinance.generateNetFlowsPdf(startYear, endYear);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf");
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
}

