# 다음수업 API Application

---
## 프로젝트 통합 개요
### 프로젝트 설명
다음 수업은 2025년부터 전면 도입되는 고교학점제에 맞춰, 학생들이 자신만의 시간표를 작성하거나 복잡한 성적 계산을 간편하게 처리할 수 있는 기능, 할 일을 잊지 않도록 TodoList를 작성하는 기능, 학생들끼리 정보를 원활히 공유할 수 있도록 돕는 커뮤니티 기능을 하나로 만든 앱 입니다.

### 개발 기간
2024년 6월부터 현재까지 진행 중

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

### 프로젝트 구조
프로젝트의 구조는 추후 자세한 정보가 제공될 예정입니다.



이 README 파일은 프로젝트의 진행 상황에 따라 계속 업데이트 될 예정입니다. 다음수업 애플리케이션과 API 서버에 관심을 가져주셔서 감사합니다.


