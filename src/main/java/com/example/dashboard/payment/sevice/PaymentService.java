package com.example.dashboard.payment.sevice;

import com.example.dashboard.common.entity.Agreement;
import com.example.dashboard.common.entity.AgreementId;
import com.example.dashboard.common.entity.Received;
import com.example.dashboard.common.entity.ReceivedId;
import com.example.dashboard.common.repository.AgreementRepository;
import com.example.dashboard.payment.dto.ReceivedRequestDto;
import com.example.dashboard.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final AgreementRepository agreementRepository;

    public void savePayment(ReceivedRequestDto request) {
        AgreementId agreementId = new AgreementId(
                request.getId().getTyGubun(),
                request.getId().getNoChasu(),
                request.getId().getCdSite(),
                request.getId().getDongho()
        );

        Agreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new IllegalArgumentException("해당 약정이 존재하지 않습니다."));

        // 기존 차수의 총 수납 금액 조회
        Long totalReceived = paymentRepository.sumReceivedAmountByAgreement(
                request.getId().getTyGubun(),
                request.getId().getNoChasu(),
                request.getId().getCdSite(),
                request.getId().getDongho()
        );

        // 기존 수납 중 가장 마지막 분납(`TY_BUNNAP`) 조회
        String lastTyBunnap = paymentRepository.findLastTyBunnap(
                request.getId().getTyGubun(),
                request.getId().getNoChasu(),
                request.getId().getCdSite(),
                request.getId().getDongho()
        );

        // 기존 분납 잔금 조회 (이전 분납 차수의 `AM_LEFT`)
        Long lastAmLeft = paymentRepository.findLastAmLeft(
                request.getId().getTyGubun(),
                request.getId().getNoChasu(),
                request.getId().getCdSite(),
                request.getId().getDongho()
        );

        // 새로운 수납 시 기존 잔금(lastAmLeft)을 반영하여 잔금 계산
        Long remainingAmount = agreement.getAmContract() - totalReceived;
        Long newAmLeft = remainingAmount - request.getAmReceived();

        // 분납 차수 결정 (이전 분납 차수(`TY_BUNNAP`)가 있으면 +1, 없으면 '01')
        String tyBunnap = lastTyBunnap == null ? "01" : String.format("%02d", Integer.parseInt(lastTyBunnap) + 1);

        if (newAmLeft >= 0) {
            // 잔금이 남아 있거나 정확히 맞아떨어지면 현재 차수에서 분납 저장
            ReceivedId receivedId = new ReceivedId(
                    tyBunnap,
                    request.getId().getTyGubun(),
                    request.getId().getNoChasu(),
                    request.getId().getDongho(),
                    request.getId().getCdSite()
            );

            Received received = Received.builder()
                    .id(receivedId)
                    .dtReceived(request.getDtReceived())
                    .amReceived(request.getAmReceived())
                    .amLeft(newAmLeft)
                    .build();

            paymentRepository.save(received);
        } else {
            // 초과 수납 발생 → 다음 차수(`NO_CHASU + 1`)로 이동
            Long excessAmount = request.getAmReceived() - remainingAmount;

            tyBunnap = "99"; // 현재 차수에서 잔금 0으로 설정

            ReceivedId receivedId = new ReceivedId(
                    tyBunnap,
                    request.getId().getTyGubun(),
                    request.getId().getNoChasu(),
                    request.getId().getDongho(),
                    request.getId().getCdSite()
            );

            Received received = Received.builder()
                    .id(receivedId)
                    .dtReceived(request.getDtReceived())
                    .amReceived(request.getAmReceived() - excessAmount) // 초과 금액 제외
                    .amLeft(0L)
                    .build();

            paymentRepository.save(received);

            // 다음 차수(`NO_CHASU + 1`)를 위한 AgreementId 생성
            String nextNoChasu = String.format("%02d", Integer.parseInt(request.getId().getNoChasu()) + 1);
            AgreementId nextAgreementId = new AgreementId(
                    request.getId().getTyGubun(),
                    nextNoChasu,
                    request.getId().getCdSite(),
                    request.getId().getDongho()
            );

            // 다음 차수 Agreement 확인 (기존 약정에서 반드시 조회)
            Agreement nextAgreement = agreementRepository.findById(nextAgreementId)
                    .orElseThrow(() -> new IllegalArgumentException("다음 차수 약정이 존재하지 않습니다."));

            // 다음 차수 수납(`TY_BUNNAP = '01'`)으로 초과 금액 추가
            ReceivedId nextReceivedId = new ReceivedId(
                    "01", // 다음 차수 첫 번째 분납
                    nextAgreementId.getTyGubun(),
                    nextAgreementId.getNoChasu(),
                    nextAgreementId.getDongho(),
                    nextAgreementId.getCdSite()
            );

            Received nextReceived = Received.builder()
                    .id(nextReceivedId)
                    .dtReceived(request.getDtReceived())
                    .amReceived(excessAmount) // 초과 금액이 다음 차수의 첫 번째 수납으로 이동
                    .amLeft(nextAgreement.getAmContract() - excessAmount)
                    .build();

            paymentRepository.save(nextReceived);
        }
    }

}




