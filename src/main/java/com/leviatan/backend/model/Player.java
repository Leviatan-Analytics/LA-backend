package com.leviatan.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player extends UUIDEntity {

    private String playerId;

    private String summonerName;

    @OneToMany
    private Set<PlayerFlag> playerFlag;

    @ManyToOne
    private Organization organization;
}
