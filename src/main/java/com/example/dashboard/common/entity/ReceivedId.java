package com.example.dashboard.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedId implements Serializable {
    @Column(name = "TY_BUNNAP")
    private String tyBunnap;

    @Column(name = "TY_GUBUN")
    private String tyGubun;

    @Column(name = "NO_CHASU")
    private String noChasu;

    @Column(name = "DONGHO")
    private String dongho;

    @Column(name = "CD_SITE")
    private String cdSite;
}


