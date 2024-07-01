## 7일차
- Spring Boot JPA 프로젝트 개발 (계속)
    0. 개념
        ```sql
        -- Oracle 전용(11g 이하 버전에서는 동작하지 않음)
        select b1_0.bno,b1_0.content,b1_0.create_date,b1_0.title 
        from board b1_0 offset 0        -- 0부터 시작해서 페이지 사이즈만큼 증가
        rows fetch first 10 rows only   -- 페이지 사이즈
        ```
    1. 페이징(중요 !)
        - /repository/BoardRepository.java에 findAll(pageable) 인터페이스 메서드 생성
        - /service/BoardService.java에 getList(page) 메서드 작성
        - /controller/BoardController.java에 list() 메서드 수정
        - /templates/board/list.html에 boardList -> paging 변경
        - /templates/board/list.html 하단에 페이징 버튼 추가, thymeleaf 기능 추가
        - /service/BoardService.java에 getList() 메서드를 최신순으로 역정렬 변경
        - /templates/board/list.html에 게시글 번호 수정
        
		<img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp006.png" width="730">


    2. /templates/board/list.html td

    3. H2 -> Oracle로 DB변경
        - build.gradle, Oracle dependencies 추가
        - application.properties Oracle 관련 설정 추가, H2 관련 설정 주석처리
        - 재시작

    4. 스프링 시큐리티(이것도 중요 !)
        - (설정) build.gradle 스프링 시큐리티 관련 디펜던시 추가
        - (설정) Gradle 재빌드, 서버 실행
        - user / 터미널 로그에 나오는 UUID 입력
            - UUID는 실행할 때 마다 변경됨
        - /security/SecurityConfig.java 보안설정 파일 생성, 작성 -> 시큐리티를 다시 풀어주기
    
        - /entity/Member.java 생성
        - /repository/MemberRepository.java 인터페이스 생성
        - /service/MemberService.java 생성 setMember() 메서드 작성
