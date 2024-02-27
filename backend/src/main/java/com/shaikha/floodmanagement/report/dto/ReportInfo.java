package com.shaikha.floodmanagement.report.dto;

import com.shaikha.floodmanagement.location.dto.LocationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportInfo {
    private long reportId;
    private String date;
    private String time;
    private LocationInfo location;
    private String filename;
}
