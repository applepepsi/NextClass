# 다음수업 API Application

---
## 프로젝트 통합 개요
### 프로젝트 설명
다음 수업은 2025년부터 전면 도입되는 고교학점제에 맞춰, 학생들이 자신만의 시간표를 작성하거나 복잡한 성적 계산을 간편하게 처리할 수 있는 기능, 할 일을 잊지 않도록 TodoList를 작성하는 기능, 학생들끼리 정보를 원활히 공유할 수 있도록 돕는 커뮤니티 기능을 하나로 만든 앱 입니다.

### 개발 기간
2024년 6월 8일 -> 2024년 9월 4일

### 팀 구성
Backend 2명, Android 1명

---
## 레포지토리 세부사항
### 기술 스택
- **Android Studio Iguana**: 2023.2.1
- **Java**: 8
- **compileSdk**: 34

## 주요 기술
---
- **Live Data**
- **ViewModel**
- **Jetpack Compose**
- **Retrofit**
- **FCM**
- **Dagger Hilt**
- **Coroutines**
- **Material Design**
- **Navigation Component**
- **Kotlin Serialization**
- **OkHttp**


### 주요 기능
- **회원 인증 기능**
   - 로그인,  회원가입,  회원탈퇴,  아이디/비밀번호 찾기, 아이디/ 비밀번호/ 정보 변경, 이메일 인증 기능 등을 구현

- **시간표 작성 기능**
   - 사용자가 원하는 시간표를 직접 작성, 수정, 삭제 할 수 있음

- **성적 확인 기능**
   - 자신이 직접 성적을 편집할 수 있고 이전에 작성한 시간표의 정보를 가져와 성적에 포함해 계산하여 보여주는 기능

- **커뮤니티 기능**
   - 게시물 작성, 수정, 삭제 기능

   - 푸쉬 알림 기능
      - 사용자가 작성한 게시물에 새로운 댓글이 추가됐다면 푸쉬 알림을 보내는 기능
      - 알림 터치 시 해당 게시물로 이동하는 기능

   - 게시물 종류
      - 모든 게시물
      - 내가 속한 학교의 학생이 작성한 게시물
      - 일정 수의 추천을 받은 베스트 게시물
      - 내가 작성한 게시물
      - 내가 댓글을 작성한 게시물

   - 게시물 검색 기능
      - 검색어에 따라 해당되는 게시물을 보여줌
      - 최근 검색어

   - 게시물 세부
      - 게시물의 수정 또는 삭제 기능
      - 게시물의 추천 기능
      - 게시물에 댓글 달기 기능

- **TO DO LIST 기능**
   - TO DO LIST를 작성 하는 기능

   - TO DO LIST 정렬 기능
      - 남은 시간 오름차순
      - 남은 시간 내림차순

   - 사용자가 설정한 시간에 맞춰 푸쉬 알림을 전송

- **사용자 설정 기능**
   - 사용자의 이메일, 비밀번호 등과 같은 몇몇 정보를 수정 할 수 있는 기능
   - 푸쉬 알림의 수신 여부를 설정 할 수 있는 기능

### 🏙 결과
| ![로그인](https://github.com/user-attachments/assets/5b032dd9-0728-4381-8661-e8627ca336fe) | ![회원가입](https://github.com/user-attachments/assets/8ea2d716-3bdf-4dc8-8856-0f7dee957646) |
|---|---|

|![홈](https://github.com/user-attachments/assets/b4b6ed25-4120-406a-bf00-c9555bb8aebe) | ![시간표](https://github.com/user-attachments/assets/d3b617a7-e42a-4bc6-a79f-47e8d2421edd) |
|---|---|

|![수업 추가하기](https://github.com/user-attachments/assets/18ca85c1-1e72-4c5b-901d-7436e381635c) | ![성적](https://github.com/user-attachments/assets/36e43af4-20d3-485a-aa13-978794502b56) |
|---|---|

|![커뮤](https://github.com/user-attachments/assets/93d3fdc6-e7b9-4331-a6cf-3133d118af33) | ![게시물 세부](https://github.com/user-attachments/assets/34e0cab4-63f7-4b81-9200-a6d2a7e378c8) |
|---|---|

|![검색](https://github.com/user-attachments/assets/d8fee59a-ccfd-4ebd-aa14-fdf04027f1c9) | ![투두](https://github.com/user-attachments/assets/476d88e3-6e6b-43ae-a197-c51b7b8c54cf) |
|---|---|

|![아이디 찾기](https://github.com/user-attachments/assets/d0a5bc64-e4d3-4d9b-9ad0-13cb5fdd2466) | ![비밀번호 찾기](https://github.com/user-attachments/assets/f77b21b0-5944-4548-99ff-ea889daa6811) |
|---|---|

|![사용자설정](https://github.com/user-attachments/assets/707e74a0-b036-456d-aba2-1a9236d3fbd2) | ![인증번호 확인](https://github.com/user-attachments/assets/f673f5c9-3b1d-4fb4-9fea-64d99a2ef2ca) |
|---|---|