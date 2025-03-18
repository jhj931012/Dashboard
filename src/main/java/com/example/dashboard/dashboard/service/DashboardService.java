package com.example.dashboard.dashboard.service;

import com.example.dashboard.dashboard.dto.DashboardDataDto;
import com.example.dashboard.dashboard.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    public List<DashboardDataDto> getDashboardData() {
        return dashboardRepository.getDashboardSummary();
    }
}
