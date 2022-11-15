package com.leviatan.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player extends UUIDEntity {

    private String playerId;

    private String summonerName;

    @ManyToOne
    private Organization organization;
}
