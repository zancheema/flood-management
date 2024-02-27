package com.shaikha.floodmanagement.notification;

import com.shaikha.floodmanagement.notification.dto.AuthorityNotificationInfo;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationCreationPayload;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationReport;
import com.shaikha.floodmanagement.notification.dto.RescueTeamNotification;
import org.springframework.http.ProblemDetail;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface NotificationService {
    boolean notifyRescueTeamMember(Principal principal, RescueNotificationCreationPayload payload);

    List<AuthorityNotificationInfo> getAuthorityManagerNotifications();

    List<RescueTeamNotification> getRescueTeamMemberNotification(Principal principal);

    Optional<RescueNotificationReport> getRescueNotificationReport(long rescueNotificationId);
}
