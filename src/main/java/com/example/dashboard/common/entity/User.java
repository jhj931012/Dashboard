package com.example.dashboard.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "USER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "ID", length = 10, nullable = false)
    private String id;

    @Column(name = "PW", length = 255, nullable = false)
    private String password;

    @Column(name = "ROLE", length = 10)
    private String role;
}

