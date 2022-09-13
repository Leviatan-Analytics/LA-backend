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
public class Organization extends UUIDEntity {

    private String name;

    @ManyToOne
    private User owner;

}
