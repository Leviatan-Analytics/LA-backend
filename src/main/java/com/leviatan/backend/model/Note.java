package com.leviatan.backend.model;

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
public class Note extends UUIDEntity{

    @ManyToOne
    private Match match;

    @ManyToOne
    private User author;

    private String text;

    @ManyToOne
    private Organization organization;

}
