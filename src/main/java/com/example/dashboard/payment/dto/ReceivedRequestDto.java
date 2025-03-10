package com.example.dashboard.payment.dto;

import com.example.dashboard.common.entity.ReceivedId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceivedRequestDto {

    private ReceivedId id;
    private LocalDate dtReceived;
    private Long amReceived;
}



