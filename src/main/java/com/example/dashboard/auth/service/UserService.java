package com.example.dashboard.auth.service;

import com.example.dashboard.common.entity.User;
import com.example.dashboard.common.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(String id, String rawPassword, String role) {
        String encodedPassword = passwordEncoder.encode(rawPassword); // 비밀번호 암호화
        User user = new User(id, encodedPassword, role);
        userRepository.save(user);
    }
}

