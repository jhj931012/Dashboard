package com.example.dashboard.auth.controller;

import com.example.dashboard.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @GetMapping
    public String showLoginPage() {
        return "login"; // login.html 로 이동
    }

    @PostMapping
    public String processLogin(@RequestParam String id, @RequestParam String password, Model model) {
        if (authService.authenticate(id, password)) {
            return "redirect:/dashboard"; // 로그인 성공 -> 대시보드 이동
        } else {
            model.addAttribute("error", "ID 또는 비밀번호가 올바르지 않습니다.");
            return "login"; // 로그인 실패 -> 로그인 페이지 유지
        }
    }
}
