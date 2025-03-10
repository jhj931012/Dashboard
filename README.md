# 🏠 Dashboard 프로젝트

### 🚀 **Spring Boot + JPA + Thymeleaf 기반의 대시보드 및 로그인 시스템**
- **Spring Security**를 적용한 로그인 기능
- **JPA + MySQL**을 활용한 데이터 관리
- **Thymeleaf 기반 UI** 구성
- **분양금 수납 및 대시보드 데이터 시각화**

---

## 📌 **기술 스택**
- **Back-End:** Spring Boot 3.x, Spring Security, JPA (Hibernate), MySQL
- **Front-End:** Thymeleaf, Bootstrap
- **Build Tool:** Gradle
- **Security:** Spring Security + BCrypt 비밀번호 암호화

---

## 📌 **주요 기능**
### **🔐 인증 및 보안**
✅ **Spring Security 기반 로그인 기능**  
✅ **BCryptPasswordEncoder를 활용한 비밀번호 암호화**  
✅ **Thymeleaf 기반 로그인 페이지 제공**  

### **💰 분양금 수납 관리**
✅ **약정 데이터(`AGREEMENT`) 및 수납 데이터(`RECEIVED`) 관리**  
✅ **복합 키(`@EmbeddedId`)를 활용한 데이터 모델링**  
✅ **수납 로직: 기존 분납 조회 및 잔금 계산 기능**  
✅ **초과 수납 시 다음 차수(`NO_CHASU + 1`)로 자동 이동**  
✅ **JPQL 기반 수납 이력 조회 및 잔금(`AM_LEFT`) 관리**  

### **📊 대시보드**
✅ **수납 데이터를 기반으로 한 시각화 제공**  
✅ **약정별 수납 내역 조회 기능**  

---

## 📂 **프로젝트 구조**

