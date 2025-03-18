package com.example.dashboard.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DashboardDataDto {
    private String cdSite;
    private String nmSite;
    private String tyGubun;
    private String noChasu;
    private Long amContract;
    private String tyBunnap;
    private Long amReceived;
    private Long amLeft;
    private LocalDate dtAgreement;
    private LocalDate dtReceived;
}
