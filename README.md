# study-springboot-2024
부경대 Java 빅데이터 과정 Spring Boot 학습 리포지토리
- 강의 내용 기록 및 복습용 리포지토리
- 강의의 주된 구성은 Thymeleaf와 JPA를 이용한 Spring Boot 사용
- MyBatis에 대한 부족한 부분은 개인적으로 학습 및 소형 프로젝트를 통해 숙지하기
- _[Spring Boot With MyBatis 개인학습/프로젝트](https://github.com/Koeyh/practice-SpringBoot)_

### 환경
- SpringBoot, 

#### 구성
- _[1. Spring Boot(3.2.6) With MyBatis](https://github.com/Koeyh/study-springboot-2024/tree/main/spring02)_
- _[2. Spring Boot(3.3.0) With JPA](https://github.com/Koeyh/study-springboot-2024/tree/main/spring03/backboard)_

## 기억해야 할 에러
```java
public Todo getTodo(@PathVariable("tno") int tno) throws Exception {
        return todoService.getTodo(tno);
    }
```
에서 _@PathVariable 뒤에 받아 올 정보를 ()내부에 입력해야한다!_

## 1일차('24. 6. 12.), 2일차
- Spring Boot 개요
    - Java Web 개발 환경 발전 과정
        - Servlet > EJB > JSP > Spring(Java 부흥기) > Spring Boot(Java 전성기)
    - 장점
        - Spring의 기술을 그대로 사용 가능(migration이 간단함)
        - JPA를 사용하면 ERD나 DB 설계 단계 없이도 손 쉽게 DB 생성 가능
            - 그래도 근본 기술인 mybatis는 개인적으로 익혀보자
        - Tomcat Webserver 내장(별도 설치 불필요)
        - 서포트 기능 다수 존재(개발이 쉬워지도록)
        - JUnit 테스트, Log4J2 로그도 모두 포함 -> Spring에서는 별도 설치가 필요함
        - JSP, **Thymeleaf**, Mustache 편하게 사용 가능
        - <u><em>DB 연동이 매우 편해짐</em></u>

    -MVC
        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/mvcInfo.png" width="730">



- Spring Boot 개발환경 설정
    - Java JDK 확인 > 17버전 이상
        - https://jdk.java.net/archive/
        - 시스템 속성 > 고급 > 환경변수 중 JAVA_HOME 설정
    - Visual Studio Code
        - VSCodeUserSetup-x64-1.90.0 exe가 아닌
        - VSCodeSetup-x64-1.90.0 exe를 설치
        - Extensions > Korean 검색, 설치
        - Extensions > Java 검색, Extension Pack for Java 설치
            - Debugger for Java 등 6개 확장팩 같이 설치
        - Extensions > Spring 검색, Spring Extension Pack 설치
            - Spring Initializr Java Support 등 3개 확장팩 같이 설치
        - Extensions > Gradle for Java 검색, 설치
    - Gradle build tool 설치 고려(기타 오류 발생 시)
        - https://gradle.org/releases/
    - Oracle latest version Docker - 일단 보류

    ##### 나중에
    - Node.js
    - React setting

- Spring Boot 프로젝트 생성(VS Code 사용 시)
    - 상단 메뉴창 > 보기 > 명령 팔레트(Ctrl + Shift + P)
        - Spring Initializr: Create a Gradle Project...
        - Specify Spring Boot version : 3.2.6 => 3.3.0
        - Specify project language : Java
        - Input Group Id : com.사용자 지정 도메인
        - Input Artifact Id : spring01 (대문자 사용 불가)
        - Specify packaging type : Jar
        - Specify Java version : 17ver 이상
        - Choose dependsncies : Selected 0 dependencies
        - 폴더 선택 Dialog 팝업 : 원하는 폴더 선택, Generate ... 버튼 클릭
        - 우측 하단 팝업에서 Open 버튼 클릭
        - Git 설정 옵션, Language Support for Java(TM) by Red Hat 설정 "항상" 버튼 클릭
    
    - TroubleShooting
        1. 프로젝트 생성이 진행되다 Gradle Connection 에러 발생
            - Extensions > Gradle for Java (코끼리!)를 제거
            - VS Code 재시작, 프로젝트 재생성
        2. Gradle 빌드 시 버전 에러로 빌드가 실패하면
            - Gradle build tool 사이트에서 에러에 표시된 버전의 Gradle bt 다운로드
            - 개발 컴퓨터에 설치
        3. ':compileJava' execution failed ... 발생 시 
            - Java JDK 잘못된 설치 x86(32bit) x64비트 혼용 설치
            - eclipse adoptium jdk 17ver 재설치, 시스템 환경설정
            - build.gradle SpringBoot Framework 버전을 조정
            - VS Code 재시작
            

    - 프로젝트 생성 후
        - /build.gradle 확인
        - src/main/resources/application.properties(또는 .yml) 확인
        - src/java/groupid/artifactID/ Java 소스 파일 위치, 작업
        - src/main/resources/ 프로젝트 설정 파일, 웹 리소스 파일(css, html, js, jsp ...)
        - Spring01Application.java   Run|Debug
        - Gradle 빌드
            - 터미널에서 .\gradlew.bat 실행
            - Gradle for Java(코끼리) > Tasks > Build > Build play icon(Run task) 실행
        - Spring Boot Dashboard
            - Apps > Spring01 > Run|Debug 중에서 하나 아이콘 클릭 서버 실행
            - Run이 아닌 Debug로 실행해야 Hot code replace가 동작됨 ! 편리성을 위해 Debug 사용 고려
                
                <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/topToolBox.png" width="350">

                
        - 브라우저 변경 설정
            - 설정(Ctrl + ,) > browser > Spring > Dashboard Open With 'Internal' -> 'external'로 변경
            - 가급적 Chorme을 기본 브라우저로 사용하는것을 추천 -> 웹 개발 시 개발자 도구 사용이 가장 용이함
    
    ## 2, 3일차
    - Oracle 도커로 설치
	- Docker는 Virtual Machine을 업그레이드한 시스템
	- 윈도우 서비스 내(services.msc) Oracle 관련 서비스 종료
	- Docker에서 Oracle 이미지 컨테이너를 다운로드 후 실행
	- Docker 설치시 오류 Docker Desktop - WSL Update failed
		- Docker Desktop 실행종료 후
		- Windows 업데이트 실행 최신판 재부팅
		- https://github.com/microsoft/WSL/releases, wsl.2.x.x.x64.msi 다운로드 설치 한 뒤
		- Docker Desktop 재실행
	- Oracle 최신판 설치
	```shell
	> docker --version
	Docker version 26.1.1, build 4cf5afa
	> docker pull container-registry.oracle.com/database/free:latest
	latest: ....
	... : Download complete
	> docker images
	REPOSITORY                                    TAG       IMAGE ID       CREATED       SIZE
	container-registry.oracle.com/database/free   latest    7510f8869b04   7 weeks ago   8.7GB
	> docker run -d -p 1521:1521 --name oracle container-registry.oracle.com/database/free
	....
	> docker logs oracle
	...
	#########################
	DATABASE IS READY TO USE!
	#########################
	...	
	> docker exec -it oracle bash
	bash-4.4$ 
	```

	- Oracle system 사용자 비번 oracle로 설정
	```shell
	bash-4.4$ ./setPassword.sh oracle
	```

	- Oracle 접속확인
		- DBeaver 탐색기 > Create > Connection

    - Database 설정
        - H2 DB -> Spring Boot에서 손쉽게 사용 가능한 Inmemory DB / Oracle, MySQL, SQLServer와 쉽게 호환
        - Oracle -> 운영 시 사용할 DB
        - MySQL -> Optional하게 설명만 진행 예정 / 개별적으로 심도있게 더 보자 !
        - Oracle에 "PKNUSB" / "pknu_p@ss" 로 ID, PW설정
            ```shell
            > sqlplus system/password
            SQL> 
            ```

    <!-- 수정 X -->
    - Spring Boot + Mybatis
        - application name : spring02
        - Spring Boot 3.3.x 에는 MyBatis 없음
        - Dependency 중 DB(H2, Oracle, MySQL 등)가 선택되어 있으면 웹 서버 초기 실행이 안됨
        - Dependency
            - Spring Boot DevTools
            - Lombok
            - Spring Web
            - Thymeleaf
            - Oracle Driver
            - Mybatis starter

        - build.gradle 확인
        
        - application.properties 추가작성

        ```properties
        spring.application.name=spring02

        ## 포트변경
        server.port=8091

        ## 로그 색상 변경
        spring.output.ansi.enabled=always

        ## 수정 사항 발생 시 서버 자동 재빌드 설정
        spring.devtools.livereload.enabled=true
        spring.devtools.restart.enabled=true

        ## 로그 레벨 설정
        logging.level.org.springframework=info
        logging.level.org.zerock=debug

        ## Oracle 설정
        spring.datasource.username=pknusb
        spring.datasource.password=pknu_p@ss
        ## Docker 사용
        ### oracle에 r이 빠져서 발생한 오류를 한참동안 못찾았다..
        spring.datasource.url=jdbc:oracle:thin@localhost:11521:FREE
        spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

        ## MyBatis 설정
        ## mapper 폴더 및에 여러가지 폴더가 내재, 확장자는 .xml이지만 어떤 파일명이건 상관X
        mybatis.mapper-locations=classpath:mapper/**/*.xml
        mybatis.type-aliases-package=com.koeyh.spring02.mapper
        ```

        - MyBatis 적용
            - Spring Boot 이전 resources/WEB-INF 위치에 root-context.xml에 DB, MyBatis 설정
            - Spring Boot 이후 application.properties + Config.java 로 변경
        
        - **MyBatis 개발 시 순서**
            <!-- 모델 -->
            0. application.properties에 spring.datasource.url=jdbc:orcle:thin:@localhost:11521:FREE, thin 뒤 :이 삭제되어 있었음.
            1. Database 테이블 생성
            2. MyBatis 설정 -> /config/MyBatisConfig.java
            3. DB의 테이블과 일치하는 Java의 클래스 생성
                - domain || entitiy || dto || vo 등
                - DB 컬럼에 작성된 _는 Java Class에서 사용하지 않음
            4. DB와 데이터를 주고받을 수 있는 클래스 생성
                - dao || **mapper** || repository 등
                - 쿼리를 클래스 내에 작성 가능, xml로 분리 가능
            5. 분리했을 경우 /resources/mapper/클래스.xml 생성, 쿼리 입력
            <!-- 컨트롤러 -->
            6. 서비스 인터페이스, 서비스 구현 클래스 생성
                - 서비스 인터페이스 : /service/*Service.java
                - 서비스 구현 클래스 : /service/*serviceImple.java 생성, 작성
            7. 사용자가 접근하는 @RestController 클래스 생성 => _Controller_ 로 변경 가능
            8. ~~경우에 따라(Optional) @SpringBootApplication 어노테이션이 존재하는 클래스에 SqlSessionFactory 빈을 생성하는 메서드 작성~~
            <!-- 뷰 -->
            9. /resources/templates/Thymeleaf html 생성, 작성

## 4일차
- Spring Boot JPA + Oracle + Thymeleaf + React
    - JPA => DB 설계를 하지 않고 Entitiy 클래스를 DB로 자동 생성 해 주는 기술, Query 생성도 불필요
    - H2  => Oracle, MySQL, SQLServer등과 달리 Inmemory DB, 스프링 부트가 실행되면 같이 실행되는 DB
        - 개발 편의성, 다른 DB로 전환 용이, 개발 과정 동안의 사용 추천(배포 전)
    - Thymeleaf => JSP의 단점인 복잡한 템플릿 형태, 스파게티코드 해소(감소)를 위한 템플릿
    - Bootstrap => 웹 디자인 및 CSS의 혁신적 기술. 정해진 틀에 커스터마이징도 가능
    - 소셜 로그인 => 구글, 카카오, 네이버 등 소셜 로그인 기능 추가
    - React => FE를 분리. BE 서버와 FE 서버를 따로 관리

- Spring Boot JPA 프로젝트 생성
    - 명령 팔레트로 시작
        - Spring Initializr: Create a Gradle Project...
        - Spring Boot version => 3.2.6
        - project language => Java
        - Group Id => com.koeyh
        - Artifact Id => backboard
        - Specify packaging type => Jar
        - Specify java version => 21
        - Choose dependencies => 
            - 초기 설정
                1. Spring Boot DevTools
                2. Lombok
                3. Spring Web
                4. Thymeleaf
            - 추후 설정
                5. Oracle Driver(later)
                6. H2 Database(later)
                7. Data JPA(later)
    - spring03 폴더 내에서 Generate into this folder

- Spring Boot JPA 프로젝트 개발 시작
    ##### 설정
    1. build.gradle 의존성(dependencies) 확인
    2. application.properties 본 설정 입력(포트번호, 로그색상, 자동재빌드 여부, 로그레벨)
    3. MVC 패턴에 맞게끔 역할별 폴더 생성(controller, serviec, entitiy ...) || 어플리케이션 기능별 폴더 생성(save, create, update ..)
        - 수행 역할별 폴더 생성 방식으로 진행
    4. /controller/MainController 생성, 기본 메서드 구현
    5. application.properties H2 데이터베이스 설정, JPA 설정 추가
    6. 웹 서버 실행, http://localhos:8080/h2-console DB 연결 확인
    ##### 개발
    7. /entity/Board.java 생성
        - GenerationType 타입
            - AUTO : SpringBoot에서 자동으로 선택(X)
            - IDENTITY : MySQL, SQLServer
            - SEQUENCE : Oracle(!)
        - column 이름을 createDate로 만들면 DB 컬럼 명이 create_date로 생성됨
            - 잘 모르겠다 싶으면 데이터베이스 해당 테이블을 조회해서 컬럼 명 확인 할 것
            - 컬럼명에 '_'를 넣지 않으려면 @column(name = "createDate") 사용
    8. /entity/Reply.java 생성
    9. 두 엔티티간 @OneToMany, @ManyToOne 관계 설정
    10. 웹 서버 재시작 후 h2-console에서 테이블 생성 확인
    11. /repository/BoardRepository.java 빈 인터페이스(JpaRepository 상속) 생성
    12. /repository/ReplyRepository.java 빈 인터페이스(JpaRepository 상속) 생성
    13. applicaition.properties ddel-auto=create => ddl-auto=update 변경
    14. /test/.../repository/BoardRepositoryTests.java 생성, 테스트 메서드 작성
    15. 테스트 시작 > 웹 서버 실행 > h2-console 확인

## 5일차
- Tip
    - Java Test중 OpenJDK 64-Bit Server VM warning: Sharing is 빨간색 경고가 뜨면
    - Ctrl + , (설정)> Java Test Config 입력 > settings.json 편집
    ```json
    "java.test.config": {
        "vmArgs": [
            "-Xshare:off"
        ]
    }
    ```
    - 저장 후 실행

- Spring Boot 프로젝트 오류 처리(Visual Studio Code)
    - 지속적인 리빌드 시에도 결과 반영이 잘 되지 않으면
    - Github(Remote Repository)에 모두 commit, push 후
    - Local Repository를 모두 삭제 후 새로 commit
    - 프로젝트 새로 불러오기, 초기화

- Spring Boot JPA 프로젝트 개발 계속
    {:start="15}
    1. jUnit 테스트로 CRUD 확인
    2. /service/BoardService.java 생성 후 getList() 메서드 작성
    3. /controller/BoardController.java 생성 후 /board/list 실행 할 수 있는 메서드 작성
    4. /templates/board/list.html 생성
        - Thymeleaf 속성
            - th:if="${board != null} => 조건문
            - th:each="board : ${boardList}" => 반복문
            - th:text="${board.title} => Java 데이터 가져오기
    5. /service/BoardService.java에 getBoard() 메서드 추가
    6. /controller/BoardController.java에 /board/detail/{bno} 실행 메서드 작성
    7. /templates/board/detail.html 생성

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp003.png" width="730">

    8. /templates/board/detail.html 댓글영역 추가
    9. /service/ReplyService.java 생성, 댓글 저장 메서드 작성
    10. /contorlle/ReplyController.java 생성, /reply/create/{bno}
    11. Bootstrap 적용
        - 다운로드 후 프로젝트에 위치
        - CDN 링크 추가
        - http://www.getbootstrap.com 다운로드 후 압축 해제
        - bootstrap.mim.css, bootstrap.min.js를 resources/satic에 위치
    12. /templates/board/list.html, detail.html에 Bootstrap 적용

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp004.png" width="730">



## 6일차
- Spring Boot JPA 프로젝트 개발 (계속)
    0. (설정) build.gradle에 Thymeleaf 레이아웃 사용을 위한 Dependency 추가
    1. Thymeleaf를 이용해 레이아웃 템플릿 생성
    2. list.html, detail.html 레이아웃 템플릿 적용
    3. /templates/layout.html에 Bootstrap CDN 적용
    4. /templates/board/list.html에 게시글 등록 버튼 추가
    5. /templates/board/create.html 게시글 작성 페이지 생성
    6. /controller/BoardController.java에 create() GetMapping 메서드 작성
    7. /service/BoardService.java setBoard() 작성
    8. /controller/BoardController.java create() PostMapping 메서드 작성
    9. _(문제) 내용을 적지 않아도 저장됨_
    10. (설정) build.gradle 입력값 검증 Spring Boot Validation 디펜던시 추가
    11. /validation/BoardForm.java 클래스 생성
    12. /controller/BoardController.java에 BoardForm을 전달 (Get/PostMapping 둘 다)
    13. create.html 입력 항목 name, id를 th:field로 변경(ex. th:field=*{title}")
    14. 댓글 등록에도 13번과 같은 기능 반영, ReplyForm, ReplyController, detail.html 작업 (11번 ~ 13번 내용과 유사)
    15. detail.html의 경고영역 div태그 내부 코드는, create.html에서 복사해서 가져올 것 (오타 방지)
    16. (문제) 각 입력창에 공백을 넣었을 때 입력되는 문제 발생 
        - @NotEmpty는 공백(스페이스)을 허용
        - @NotBlank로 대체하여 해결

		<img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/sp005.png" width="730">
		

    17. 네비게이션바(navbar) 추가
    18. /templates/layout.html에 추가
    19. 페이징 처리를 위해 테스트로 대량 데이터 추가

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


## 9일차
- Spring Boot JPA 프로젝트 개발 (계속)
    1. 수정, 삭제
        - /entity/Board, Reply.java 수정일자 필드 추가
        - /templates/board/detail.html 수정, 삭제버튼 추가
            - sec:authorize="isAuthenticated()"가 없으면 500 에러 발생
        - 삭제 버튼은 바로 실행되지 않게 javascript 추가
        - /controller/BoardController.java에 modify() GET 메서드 작성
        - /templates/board/create.html 수정(생성, 수정이 같은 페이지에서 진행)
            - form 태그 내 th:action부분 삭제 (이유는 다음과 같음)
            - Get이 /board/create로 들어가면 post도 /board/create로 실행되고
            - /board/modify/{bno}로 들어가면 post도 /board/modify로 실행되기 떄문에 별도 action 지정 불필요
        - /service/BoardService.java에 수정 관련된 메서드 추가작성
        - /controller/BoardController.java에 modify() POST 메서드 작성
            - html에는 BoardForm 객체 값이 들어있음. 컨트롤러에서 받아서 Board 객체를 다시 만들어 서비스로 전달

        - /service/BoardService.java에 삭제관련 메서드 추가
        - /controller/BoardController.java에 delete() GET 메서드 작성

        - /templates/board/detail.html 댓글 수정, 삭제버튼 추가
        - /service/ReplyService.java에 댓글 수정, 삭제 관련 메서드 추가
        - /controller/ReplyController.java modify GET, POST 메서드 작성, 삭제 GET 메서드 작성
        - /templates/reply/modify.html 생성, 작성

        - /templates/board/detail.html에 게시글, 댓글 수정날짜 표시


        
    2. 앵커 기능 _(이전 프로젝트에서 구현하지 못한 기능)_
        - 추가, 수정, 삭제 시 이전 자신의 위치로 되돌아가는 기능
        - /template/board/detail.html 댓글마다 앵커링 추가
        - /controller/ReplyController.java에 modify Post매핑 부분 return에 앵커링 추가
        - /service/ReplyService.java 생성 메서드 void -> Reply 변경 
        - /controller/ReplyController.java create Post 메서드를 변경

        - /controller/BoardController.java에 detail() 메서드 수정

    3. 검색 기능
        - /service/BoardService.java에 search() 메서드 추가
        - /repository/BoardRepository.java 메서드 추가
        - /service/BoardService.java에 getList() 메서드 추가생성
        - /controller/BoardController.java에 list()메서드 추가생성
        - /templates/board/list.html에 검색창 추가, searchForm 폼 영역 추가, **페이징 영역 수정, Javascript 추가**

## 10일차
- Spring Boot JPA 프로젝트 개발 (계속)
    1. 검색기능(계속) - JPA Query
        - @Query 어노테이션으로 직접 쿼리를 작성
        - ***@Query는 단순 쿼리가 아니기 때문에 JpaRepository가 자동으로 만들어 줄 수 없을 때 사용***
        - ***DB의 표준 Query와는 상이함(Java의 Entity와 일치)***
        - /repository/BoardRepository.java의 findAllByKeyword() 메서드 추가
        - JPA Query @Query(" ... ")에 작성
        - /service/BoardService.java getList()수정

    4. 마크다운 적용, 마크다운 에디터 추가
        - Wysiwyg 에디터 - CKEditor(https://ckeditor.com/), TinyMCE
        - simplemde(https://simplemde.com/) View On Github 클릭 >> CDN 링크 복사, layout.html 링크 추가
        - create.html, textarea id content를 simplemde로 변환하는 js 추가

        - (설정) build.gradle 마크다운 뷰 의존성 추가
        - /common/CommonUtil.java 생성
        - /templates/board/detail.html 마크다운 뷰어 적용
            - th:text를 ***th:utext로 변경***
            - ```java
                <div th:utext="${@commonUtil.markdown( board.content )}" class="card-text"></div>
            ```

    3 카테고리 추가(게시판, QnA , 공지사항)
    - 비밀번호 찾기, 비밀번호 변경
    - 조회수 추가

    - React 적용 고민
    - 리액트로 프론트엔드 설정
    - 타임리프 - 리액트로 변경
    - 스프링부트 RestAPI 작업 

    - AWS 라이트세일
    - 서버 접속 프로그램 설정
    - 웹 서버 배포
    - 8080 -> 80 서버로 변경
    - http -> https 변경
