package com.example.dashboard.dashboard.repository;

import com.example.dashboard.dashboard.dto.DashboardDataDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Object, Long> {

    @Query("SELECT new com.example.dashboard.dashboard.dto.DashboardDataDto(" +
            "s.cdSite, s.nmSite, a.tyGubun, a.noChasu, a.amContract, " +
            "r.tyBunnap, r.amReceived, r.amLeft, a.dtAgreement, r.dtReceived) " +
            "FROM Agreement a " +
            "LEFT JOIN Received r ON a.cdSite = r.cdSite AND a.dongho = r.dongho " +
            "LEFT JOIN Site s ON a.cdSite = s.cdSite")
    List<DashboardDataDto> getDashboardSummary();
}
