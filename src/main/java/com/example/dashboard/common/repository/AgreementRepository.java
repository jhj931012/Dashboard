package com.example.dashboard.common.repository;

import com.example.dashboard.common.entity.Agreement;
import com.example.dashboard.common.entity.AgreementId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgreementRepository extends JpaRepository<Agreement, AgreementId> {
    // 필요하면 추가적인 쿼리 메서드 정의 가능
}
