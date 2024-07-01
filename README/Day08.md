## 8일차
- Spring Boot JPA 프로젝트 개발 (계속)
    1. Spring Security 계속
        - /security/SecurityConfig.java에 BCryptPasswordEncoder를 Bean으로 생성하는 작업
        - /validation/MemberForm.java 생성
        - /controller/MemberController.java 생성
        - /entity/Member.java에 regDate 추가
        - /service/MemverService.java regDate() 부분 추가 작성
            - 회원가입 시 가입 일시 데이터 보관용
        - /templates/member/register.html 생성
        - (설정) Member 테이블에 저장된 회원정보 확인
        - /templates/layout.html에 회원가입 링크 버튼 추가
        - /controller/MemberController.java Postmapping register에 중복회원가입 방지 추가
        - /security/MemberRole.java enum으로 ROLE_ADMIN, ROLE_USER 생성
        - /entity/Member.java role 변수 추가
        
    2. 로그인 기능 구현
        - /security/SecurityConfig.java에 login url 설정
        - /templates/layout.html 로그인 링크 수정
        - /templates/member/login.html 생성
        - /repository/MemberRepository.java에 find관련 메서드 추가
        - /controller/MemberController.java에 login GetMapping 메서드 작성
            - /service/MemberSecurityService.java => login은 Post를 사용하지 않고, Spring Security가 지원하는 UserDetailsService 클래스를 사용
        - /security/SecurityConfig.java에 계정 관리자 Bean 추가
        - /templates/layout.html 로그인/로그아웃 토글 메뉴 추가

    3. 게시글 작성자 추가
        - /entity/Board.java에 작성자 변수(속성) 추가
        - /entity/Reply.java에 작성자 변수(속성) 추가
        - /service/MemberService.java에 getMember() 메서드 추가
        - (Tip) default Exception으로 예외를 처리하면 메서드 뒤에 항상 throws Exception을 적어줘야 한다
        - commin/DataNotFoundException.java 생성 -> throws Exception 쓰는데 반영
        - /service/ReplyService.java setReply() 사용자 추가
        - /controller/ReplyController.java 오류나는 setReply() 파라미터 수정
        - /service/BoardService.java ..
        - /controller/BoardController.java의 setBoard() 사용자 추가
        - /controller/ 작성하는 GetMapping, PostMapping 메서드에 @PreAuthorize 어노테이션 추가
        - /config/SecurityConfig.java @PreAuthorize 동작하도록 설정 추가
        - /templates/board/detail.html에 답변 textarea를 로그인 전, 후로 구분 작업

        - /templates/board/list.html table 태그에 작성자 컬럼 추가
        - /templates/board/detail.html 게시글 작성자, 댓글 작성자 표시 추가


    	<img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp007.png" width="730">

