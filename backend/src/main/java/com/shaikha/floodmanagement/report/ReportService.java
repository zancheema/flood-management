package com.shaikha.floodmanagement.report;

import com.shaikha.floodmanagement.report.dto.ReportInfo;
import org.springframework.http.ProblemDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ReportService {
    Optional<ReportInfo> createReport(long droneId, double locationLat, double locationLng, MultipartFile img);

    Optional<ReportInfo> getAuthorityReport(long reportId);
}
