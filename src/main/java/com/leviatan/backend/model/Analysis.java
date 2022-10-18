package com.leviatan.backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.leviatan.backend.dto.ReducedAnalysisDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "analysis")
@Data
public abstract class Analysis extends UUIDEntity{

    @ManyToOne
    private Organization organization;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime analysisDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime matchDate;

    private String matchId;

    private Long matchDuration;

    public abstract ReducedAnalysisDto toReducedDto();
}
