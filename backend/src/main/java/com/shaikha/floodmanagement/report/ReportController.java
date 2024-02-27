package com.shaikha.floodmanagement.report;

import com.shaikha.floodmanagement.report.dto.ReportInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/authority/{reportId}")
    public ResponseEntity<ReportInfo> getAuthorityReport(@PathVariable("reportId") long reportId) {
        return ResponseEntity.of(reportService.getAuthorityReport(reportId));
    }
}
