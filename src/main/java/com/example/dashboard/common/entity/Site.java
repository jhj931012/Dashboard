package com.example.dashboard.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SITE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Site {

    @Id
    @Column(name = "CD_SITE")
    private String cdSite;

    @Column(name = "NM_SITE")
    private String nmSite;

    @OneToMany(mappedBy = "site")
    private List<Agreement> agreements = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<Received> receiveds = new ArrayList<>();

    @OneToMany(mappedBy = "site")
    private List<Contractor> contractors = new ArrayList<>();
}

