package com.example.dashboard.payment.repository;

import com.example.dashboard.common.entity.Received;
import com.example.dashboard.common.entity.ReceivedId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Received, ReceivedId> {

    // 기존 차수의 총 수납 금액 조회
    @Query("""
        SELECT COALESCE(SUM(r.amReceived), 0)
        FROM Received r
        WHERE r.id.tyGubun = :tyGubun
        AND r.id.noChasu = :noChasu
        AND r.id.cdSite = :cdSite
        AND r.id.dongho = :dongho
        """)
    Long sumReceivedAmountByAgreement(
            @Param("tyGubun") String tyGubun,
            @Param("noChasu") String noChasu,
            @Param("cdSite") String cdSite,
            @Param("dongho") String dongho
    );

    // 가장 최근 분납 차수(`TY_BUNNAP`) 조회
    @Query("""
        SELECT MAX(r.id.tyBunnap)
        FROM Received r
        WHERE r.id.tyGubun = :tyGubun
        AND r.id.noChasu = :noChasu
        AND r.id.cdSite = :cdSite
        AND r.id.dongho = :dongho
        """)
    String findLastTyBunnap(
            @Param("tyGubun") String tyGubun,
            @Param("noChasu") String noChasu,
            @Param("cdSite") String cdSite,
            @Param("dongho") String dongho
    );

    // 가장 최근 분납(`TY_BUNNAP`)의 잔금(`AM_LEFT`) 조회
    @Query("""
        SELECT COALESCE(r.amLeft, 0)
        FROM Received r
        WHERE r.id.tyGubun = :tyGubun
        AND r.id.noChasu = :noChasu
        AND r.id.cdSite = :cdSite
        AND r.id.dongho = :dongho
        AND r.id.tyBunnap = (
            SELECT MAX(rr.id.tyBunnap)
            FROM Received rr
            WHERE rr.id.tyGubun = r.id.tyGubun
            AND rr.id.noChasu = r.id.noChasu
            AND rr.id.cdSite = r.id.cdSite
            AND rr.id.dongho = r.id.dongho
        )
        """)
    Long findLastAmLeft(
            @Param("tyGubun") String tyGubun,
            @Param("noChasu") String noChasu,
            @Param("cdSite") String cdSite,
            @Param("dongho") String dongho
    );
}


