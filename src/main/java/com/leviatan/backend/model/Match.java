package com.leviatan.backend.model;

import com.leviatan.backend.model.league.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match extends UUIDEntity {

    private String matchId;

    private Region region;

    @ManyToOne
    private User user;

}
