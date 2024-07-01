## ***12일차***
1. 에러페이지 작업(404, 500, etc)
    - 순서 : 설정 => 이미지 생성 => 뷰 생성 => 컨트롤러 생성
    - application.properties에서 에러 페이지 관련 설정 추가
    - resource/static 에 img 폴더 생성 후 bg_error.jpg 저장
    - /resource/templates에 error 폴더 생성 후 하위에 404.html, 500.html, error.html 페이지 생성
    - Thymeleaf가 아닌 단순 HTML로 에러 페이지를 생성하므로 layout.html사용없이, custom.css를 생성하여 참조
    - /controller/CustomErrorController.java 생성
    
2. 비밀번호 변경
    - build.gradle 메일을 보내기 위한 의존성 추가
    - application.properties 메일설정(네이버) 입력
    - 네이버 메일 설정
        - 설정 >> 환경설정 >> POP3/SMTP 설정

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp009.png" width="730">

    - ***/config/SecurityConfig.java CSRF 설정 변경***
    - /service/MailService.java 생성
    - /restcontroller/MailController.java 생성
    - https://www.postman.com/ 다운로드