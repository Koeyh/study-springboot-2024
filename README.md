# study-springboot-2024
부경대 Java 빅데이터 과정 Spring Boot 학습 리포지토리
- 강의 내용 기록 및 복습용 리포지토리
- 강의의 주된 구성은 Thymeleaf와 JPA를 이용한 Spring Boot 사용
- MyBatis에 대한 부족한 부분은 개인적으로 학습 및 소형 프로젝트를 통해 숙지하기
- _[Spring Boot With MyBatis 개인학습/프로젝트](https://github.com/Koeyh/practice-SpringBoot)_

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
    16. jUnit 테스트로 CRUD 확인
    17. /service/BoardService.java 생성 후 getList() 메서드 작성
    18. /controller/BoardController.java 생성 후 /board/list 실행 할 수 있는 메서드 작성
    19. /templates/board/list.html 생성
        - Thymeleaf 속성
            - th:if="${board != null} => 조건문
            - th:each="board : ${boardList}" => 반복문
            - th:text="${board.title} => Java 데이터 가져오기
    20. /service/BoardService.java에 getBoard() 메서드 추가
    21. /controller/BoardController.java에 /board/detail/{bno} 실행 메서드 작성
    22. /templates/board/detail.html 생성
    23. 
