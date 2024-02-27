package com.shaikha.floodmanagement.report;

import com.shaikha.floodmanagement.drone.Drone;
import com.shaikha.floodmanagement.location.Location;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue
    private long reportId;

//    @Column(nullable = false)
//    private String date;
//
//    @Column(nullable = false)
//    private String time;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @OneToOne(optional = false)
    private Location location;

    private String peopleMediaName;

    @Column(nullable = false)
    private String vehicleMediaName;

    @ManyToOne
    private Drone drone;

//    @ManyToMany(mappedBy = "reports")
//    private List<User> users;
}
