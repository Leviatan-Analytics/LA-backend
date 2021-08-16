package com.leviatan.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_data")
public class User extends UUIDEntity{
    private String username;
    private String email;
    private String password;
}
