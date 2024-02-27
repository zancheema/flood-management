package com.shaikha.floodmanagement.notification;

import com.shaikha.floodmanagement.authority.AuthorityManager;
import com.shaikha.floodmanagement.report.Report;
import com.shaikha.floodmanagement.rescue.RescueTeam;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueNotification {
    @Id
    @GeneratedValue
    private long notificationId;

    @ManyToOne
    private AuthorityManager authorityManager;

    @ManyToOne
    private RescueTeam rescueTeam;

    @ManyToOne
    private Report report;

    @Column(nullable = false)
    private LocalDateTime datetime;

    private String name;

    private String subject;

    private String message;
}
