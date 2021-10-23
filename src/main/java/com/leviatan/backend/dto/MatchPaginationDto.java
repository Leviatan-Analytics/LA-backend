package com.leviatan.backend.dto;

import com.leviatan.backend.model.pagination.AnalysisType;
import com.leviatan.backend.model.pagination.AnalysisSort;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchPaginationDto {
    private Integer size;
    private Integer page;
    private Sort.Direction direction;
    private AnalysisSort property;
    private AnalysisType analysisType;

}
