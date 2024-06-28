## ***13일차***
- Spring Boot JPA 프로젝트 개발 계속
    0. 메일 작업 중 생긴 오류
        - 로그인 후 게시글 작성 시 500에러 발생
        - CSRF토큰 떄문에 발생하는 오류 
        - /templates/board/create.html, /templates/reply/modify 에 있는 CSRF관련 태그 주석처리! 

    1. 비밀번호 초기화(계속)
        - /templates/member/login.html 비밀번호 초기화 버튼
        - /controller/MemberController.java reset()메서드 추가
        - /templates/member/reset.html 생성 -> register.html 가져와서 작성 (비밀번호 초기화 뷰)
        - /controller/MailController.java 생성, /mail/reset-mail GET매핑 메서드 생성
        - /service/MemberService.java에 메일 주소로 검색하는 메서드 getMemberByEmail() 추가 
        - /service/MailService.java에 메일 전송 메서드 생성, 수정
            - UUID를 생성해서 메일로 전송하는 기능 추가
        - /entity/Reset.java 생성 -> H2콘솔에서 해당 테이블이 생성되는지 확인

        - /repository/ResetRepository.java 생성
        - /service/ResetService.java 생성

        - http://localhost:8080/user/resetpassword (회원가입과 유사하게 개발)
        - 비밀번호 초기화 화면으로 이동
        - 비밀번호, 비밀번호 확인 입력

    2. 구글 로그인
        - https://console.cloud.google.com/ 구글 클라우드 콜솔
        - 프로젝트 생성
        - OAuth 동의화면 설정
        - 계속 진행..