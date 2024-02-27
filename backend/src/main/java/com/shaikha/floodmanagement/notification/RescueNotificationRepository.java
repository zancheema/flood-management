package com.shaikha.floodmanagement.notification;

import com.shaikha.floodmanagement.rescue.RescueTeam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RescueNotificationRepository extends JpaRepository<RescueNotification, Long> {
    List<RescueNotification> findAllByRescueTeam(RescueTeam rescueTeam);
}
