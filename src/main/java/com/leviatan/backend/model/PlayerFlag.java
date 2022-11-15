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
public class PlayerFlag extends UUIDEntity {

    @ManyToOne
    private Player player;

    @ManyToOne
    private User user;

}
