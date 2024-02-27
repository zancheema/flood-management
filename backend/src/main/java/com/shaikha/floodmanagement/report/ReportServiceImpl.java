package com.shaikha.floodmanagement.report;

import com.shaikha.floodmanagement.drone.Drone;
import com.shaikha.floodmanagement.drone.DroneRepository;
import com.shaikha.floodmanagement.location.Location;
import com.shaikha.floodmanagement.location.LocationRepository;
import com.shaikha.floodmanagement.location.dto.LocationInfo;
import com.shaikha.floodmanagement.report.dto.ReportInfo;
import com.shaikha.floodmanagement.s3.StorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.shaikha.floodmanagement.util.MappingUtil.*;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final StorageService storageService;
    private final LocationRepository locationRepository;
    private final DroneRepository droneRepository;

    public ReportServiceImpl(ReportRepository reportRepository, StorageService storageService, LocationRepository locationRepository, DroneRepository droneRepository) {
        this.reportRepository = reportRepository;
        this.storageService = storageService;
        this.locationRepository = locationRepository;
        this.droneRepository = droneRepository;
    }

    @Override
    public Optional<ReportInfo> createReport(long droneId, double locationLat, double locationLng, MultipartFile img) {
        String fileName = storageService.uploadFile(img);
        LocalDateTime datetime = LocalDateTime.now();
        Location location = locationRepository.save(new Location(0L, locationLat, locationLng));
        Optional<Drone> drone = droneRepository.findById(droneId);
        if (drone.isEmpty()) {
            return Optional.empty();
        }

        Report report = new Report(0L, datetime, location, null, fileName, drone.get());
        Report savedReport = reportRepository.save(report);
        ReportInfo reportInfo = getReportInfo(savedReport);

        return Optional.of(reportInfo);
    }

    @Override
    public Optional<ReportInfo> getAuthorityReport(long reportId) {
        return reportRepository.findById(reportId)
                .map(this::getReportInfo);
    }

    private ReportInfo getReportInfo(Report report) {
        return new ReportInfo(
                report.getReportId(),
                getReportDate(report.getDatetime()),
                getReportTime(report.getDatetime()),
                new LocationInfo(report.getLocation().getLat(), report.getLocation().getLng()),
                report.getVehicleMediaName()
        );
    }
}
