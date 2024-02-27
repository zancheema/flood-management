package com.shaikha.floodmanagement.notification;

import com.shaikha.floodmanagement.authority.AuthorityManagerRepository;
import com.shaikha.floodmanagement.location.LocationRepository;
import com.shaikha.floodmanagement.location.dto.LocationInfo;
import com.shaikha.floodmanagement.notification.dto.AuthorityNotificationInfo;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationCreationPayload;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationReport;
import com.shaikha.floodmanagement.notification.dto.RescueTeamNotification;
import com.shaikha.floodmanagement.report.Report;
import com.shaikha.floodmanagement.report.ReportRepository;
import com.shaikha.floodmanagement.rescue.RescueTeam;
import com.shaikha.floodmanagement.rescue.RescueTeamRepository;
import com.shaikha.floodmanagement.user.User;
import com.shaikha.floodmanagement.user.UserRepository;
import com.shaikha.floodmanagement.util.MappingUtil;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.shaikha.floodmanagement.util.MappingUtil.getNotificationTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final RescueNotificationRepository rescueNotificationRepository;
    private final AuthorityManagerRepository authorityManagerRepository;
    private final RescueTeamRepository rescueTeamRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final ReportRepository reportRepository;

    public NotificationServiceImpl(RescueNotificationRepository rescueNotificationRepository, AuthorityManagerRepository authorityManagerRepository, RescueTeamRepository rescueTeamRepository, LocationRepository locationRepository, UserRepository userRepository, ReportRepository reportRepository) {
        this.rescueNotificationRepository = rescueNotificationRepository;
        this.authorityManagerRepository = authorityManagerRepository;
        this.rescueTeamRepository = rescueTeamRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean notifyRescueTeamMember(Principal principal, @RequestBody @Valid RescueNotificationCreationPayload payload) {
        User authorityUser = userRepository.findByUsername(principal.getName()).get();
        Optional<Report> report = reportRepository.findById(payload.getReportId());

        Optional<User> rescueUser = userRepository.findByEmail(payload.getRescueTeamEmail());

        if (authorityUser.getAuthorityManager() == null || report.isEmpty() || rescueUser.isEmpty()) {
            return false;
        }

        RescueTeam rescueTeam = rescueUser.get().getRescueTeam();
        if (rescueTeam == null) return false;


        RescueNotification rescueNotification = new RescueNotification(
                0L,
                authorityUser.getAuthorityManager(),
                rescueTeam,
                report.get(),
                LocalDateTime.now(),
                payload.getName(),
                payload.getSubject(),
                payload.getMessage()
        );
        rescueNotificationRepository.save(rescueNotification);
        return true;
    }

    @Override
    public List<AuthorityNotificationInfo> getAuthorityManagerNotifications() {
        return reportRepository.findAll()
                .stream()
                .sorted((a, b) -> b.getDatetime().compareTo(a.getDatetime()))
                .map(report -> new AuthorityNotificationInfo(
                        report.getDrone().getDroneId(),
                        report.getReportId(),
                        getNotificationTime(report.getDatetime())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<RescueTeamNotification> getRescueTeamMemberNotification(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).get();
        RescueTeam rescueTeam = user.getRescueTeam();
        List<RescueNotification> notifications = rescueNotificationRepository.findAllByRescueTeam(rescueTeam);
        return notifications
                .stream()
                .sorted((a, b) -> b.getDatetime().compareTo(a.getDatetime()))
                .map(notification -> new RescueTeamNotification(
                        notification.getAuthorityManager().getAuthorityManagerId(),
                        getNotificationTime(notification.getDatetime()),
                        notification.getNotificationId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RescueNotificationReport> getRescueNotificationReport(long rescueNotificationId) {
        return rescueNotificationRepository.findById(rescueNotificationId)
                .map(notification -> new RescueNotificationReport(
                        notification.getAuthorityManager().getAuthorityManagerId(),
                        notification.getName(),
                        notification.getSubject(),
                        notification.getMessage(),
                        MappingUtil.getReportDate(notification.getDatetime()),
                        MappingUtil.getReportTime(notification.getDatetime()),
                        MappingUtil.getLocationInfo(notification.getReport().getLocation()),
                        notification.getReport().getVehicleMediaName()
                ));
    }
}
