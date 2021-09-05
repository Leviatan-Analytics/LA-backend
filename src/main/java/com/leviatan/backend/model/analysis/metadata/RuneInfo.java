package com.leviatan.backend.model.analysis.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuneInfo implements Serializable {
    private String keystoneRuneType;
    private String keystoneRuneId;
    private String primaryRuneTreeType;
    private String primaryRuneTreeId;
    private String secondaryRuneTreeType;
    private String secondaryRuneTreeId;
}
