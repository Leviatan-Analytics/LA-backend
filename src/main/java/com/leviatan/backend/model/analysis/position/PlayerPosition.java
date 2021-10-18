package com.leviatan.backend.model.analysis.position;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPosition implements Serializable {
    private String username;
    private Integer x;
    private Integer y;
}
