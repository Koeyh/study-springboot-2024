## ***12일차***
1. 에러페이지 작업(404, 500, etc)
    - 순서 : 설정 => 이미지 생성 => 뷰 생성 => 컨트롤러 생성
    - application.properties에서 에러 페이지 관련 설정 추가
    - resource/static 에 img 폴더 생성 후 bg_error.jpg 저장
    - /resource/templates에 error 폴더 생성 후 하위에 404.html, 500.html, error.html 페이지 생성
    - Thymeleaf가 아닌 단순 HTML로 에러 페이지를 생성하므로 layout.html사용없이, custom.css를 생성하여 참조
    - /controller/CustomErrorController.java 생성
    
2. 비밀번호 변경
    - 비밀번호 초기화 기능 추가(메일서버 세팅)
    - 비밀번호 초기화 화면으로 이동
    - 비밀번호, 비밀번호 확인 입력
    

3. 소셜 로그인(카카오, 네이버, 구글) 구글