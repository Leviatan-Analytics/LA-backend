package com.leviatan.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.model.league.Region;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Match extends UUIDEntity {

    private String matchId;
    private Region region;
    private String tournamentName;
    private Long duration;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime matchDate;

    @ManyToOne
    private Organization organization;

}
