package com.shaikha.floodmanagement.notification;

import com.shaikha.floodmanagement.notification.dto.AuthorityNotificationInfo;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationCreationPayload;
import com.shaikha.floodmanagement.notification.dto.RescueNotificationReport;
import com.shaikha.floodmanagement.notification.dto.RescueTeamNotification;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/new/rescue")
    public ResponseEntity<?> notifyRescueTeamMember(
            Principal principal,
            @RequestBody @Valid RescueNotificationCreationPayload payload
    ) {
        boolean success = notificationService.notifyRescueTeamMember(principal, payload);
        if (success) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/authority")
    public List<AuthorityNotificationInfo> getAuthorityManagerNotifications() {
        return notificationService.getAuthorityManagerNotifications();
    }

    @GetMapping("/rescue")
    public List<RescueTeamNotification> getRescueTeamMemberNotification(Principal principal) {
        return notificationService.getRescueTeamMemberNotification(principal);
    }

    @GetMapping("/rescue/{rescueNotificationId}/report")
    public ResponseEntity<RescueNotificationReport> getRescueNotificationReport(
            @PathVariable("rescueNotificationId") long rescueNotificationId
    ) {
        return ResponseEntity.of(notificationService.getRescueNotificationReport(rescueNotificationId));
    }
}
