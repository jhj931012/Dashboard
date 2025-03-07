package com.example.dashboard.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONTRACTOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contractor {

    @EmbeddedId
    private ContractorId id; // 복합키

    @ManyToOne
    @JoinColumn(name = "CD_SITE", insertable = false, updatable = false)
    private Site site;  // N:1 관계 (CONTRACTOR -> SITE)

    @OneToMany(mappedBy = "contractor")
    private List<Agreement> agreements = new ArrayList<>(); // 1:N 관계 (CONTRACTOR -> AGREEMENT)

    @Column(name = "CONTRACTOR")
    private String contractor;

    @Column(name = "YN_CONTRACT")
    private Boolean ynContract;

    @Column(name = "DT_CONTRACT")
    private LocalDate dtContract;
}
