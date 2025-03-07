package com.example.dashboard.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AGREEMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agreement {

    @EmbeddedId
    private AgreementId id;  // 복합키

    @ManyToOne
    @JoinColumn(name = "CD_SITE", insertable = false, updatable = false)
    private Site site;  // N:1 관계 (AGREEMENT -> SITE)

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "DONGHO", referencedColumnName = "DONGHO", insertable = false, updatable = false),
            @JoinColumn(name = "CD_SITE", referencedColumnName = "CD_SITE", insertable = false, updatable = false)
    })
    private Contractor contractor; // N:1 관계 (AGREEMENT -> CONTRACTOR)

    @Column(name = "DT_AGREEMENT")
    private LocalDate dtAgreement;

    @Column(name = "AM_CONTRACT")
    private Long amContract;
}

