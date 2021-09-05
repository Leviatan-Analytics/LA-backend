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
public class ItemInfo implements Serializable {
    private String itemName;
    private String itemId;
    private String slot;
}
