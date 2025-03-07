package com.example.dashboard.auth.service;

import com.example.dashboard.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean authenticate(String id, String rawPassword) {
        return userRepository.findById(id)
                .map(user -> passwordEncoder.matches(rawPassword, user.getPassword())) // 비밀번호 비교
                .orElse(false);
    }
}
