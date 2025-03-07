package com.example.dashboard.common.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "RECIEVED")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Received {

    @EmbeddedId
    private ReceivedId id;  // 복합키

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TY_GUBUN", referencedColumnName = "TY_GUBUN", insertable = false, updatable = false),
            @JoinColumn(name = "NO_CHASU", referencedColumnName = "NO_CHASU", insertable = false, updatable = false),
            @JoinColumn(name = "DONGHO", referencedColumnName = "DONGHO", insertable = false, updatable = false)
    })
    private Agreement agreement;  // N:1 관계 (RECIEVED -> AGREEMENT)

    @ManyToOne
    @JoinColumn(name = "CD_SITE", insertable = false, updatable = false)
    private Site site;  // N:1 관계 (RECIEVED -> SITE)

    @Column(name = "DT_RECEIVED")
    private LocalDate dtReceived;

    @Column(name = "AM_RECEIVED")
    private Long amReceived;

    @Column(name = "AM_LEFT")
    private Long amLeft;
}

