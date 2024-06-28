## ***13일차***
- Spring Boot JPA 프로젝트 개발 계속
  
    1. 메일 작업 중 생긴 오류
        - 로그인 후 게시글 작성 시 500에러 발생
        - CSRF토큰 떄문에 발생하는 오류 
        - /templates/board/create.html, /templates/reply/modify 에 있는 CSRF관련 태그 주석처리! 

    2. 비밀번호 초기화(계속)
        - /templates/member/login.html 비밀번호 초기화 버튼
        - /controller/MemberController.java reset()메서드 추가
        - /templates/member/reset.html 생성 -> register.html 가져와서 작성 (비밀번호 초기화 뷰)
        - /controller/MailController.java 생성, /mail/reset-mail GET매핑 메서드 생성
        - /service/MemberService.java에 메일 주소로 검색하는 메서드 getMemberByEmail() 추가 
        - /service/MailService.java에 메일 전송 메서드 생성, 수정
            - UUID를 생성해서 메일로 전송하는 기능 추가


        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp010.PNG" width="730">


        - /entity/Reset.java 생성 -> H2콘솔에서 해당 테이블이 생성되는지 확인

        - /repository/ResetRepository.java 생성
            - findByUuid() 추가
        - /service/ResetService.java 생성
        - /service/MailService.java에 ResetService 객체 생성, 메일 전송 후 setReset() 사용
            - _service에서 service 객체를 생성할 일이 잘 없으니 이런 경우도 기억해두자_
        - /controller/MemberController.java에 /member/reset-password GetMapping 메서드 작성
            - 파라미터로 uuid를 전달받아 reset 객체에 해당 uuid와 일치하는 email에 해당하는 사용자 정보 조회
        - /templates/member/newpassword.html 생성
            - input 태그에서 disabled 사용 시 데이터 전달이 되지않아 목적과 다른 실행결과 발생
            - readonly 속성 사용하여 disabled와 동일한 폼으로 변경
        - /controller/MemberController.java의 /member/reset-password @PostMapping 메서드 작성
        - /service/MemberService.java의 setMember() 메서드 추가
        - 새로 입력받은 패스워드에 인코딩을 적용하지 않아 오류 발생, 인코딩 적용으로 해결

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp011.PNG" width="730">
