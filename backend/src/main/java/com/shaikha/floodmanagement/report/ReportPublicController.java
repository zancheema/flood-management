package com.shaikha.floodmanagement.report;

import com.shaikha.floodmanagement.report.dto.ReportInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/public/reports")
@Slf4j
public class ReportPublicController {
    private final ReportService reportService;

    public ReportPublicController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/new")
    public ResponseEntity<?> createReport(
            @RequestParam("droneId") long droneId,
            @RequestParam("locationLat") double locationLat,
            @RequestParam("locationLng") double locationLng,
            @RequestParam("img") MultipartFile img
    ) {
        Optional<ReportInfo> result = reportService.createReport(droneId, locationLat, locationLng, img);
        if (result.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(result.get());
    }
}
