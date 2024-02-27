package com.shaikha.floodmanagement.rescue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RescueTeam {
    @Id
    @GeneratedValue
    private long rescueTeamId;
}
