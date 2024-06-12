# study-springboot-2024
부경대 Java 빅데이터 과정 Spring Boot 학습 리포지토리
- 강의 내용 기록 및 복습용 리포지토리

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
    <u> - _DB 연동이 매우 편해짐_</u>

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
        - Specify Spring Boot version : 3.2.6
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
            - build.gradle SpringBoot Framework 버전을 다운 3.3.0 -> 3.1.5
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
    
    - Database 설정
        - H2 DB -> Spring Boot에서 손쉽게 사용 가능한 Inmemory DB / Oracle, MySQL, SQLServer와 쉽게 호환
        - Oracle -> 운영 시 사용할 DB
        - MySQL -> Optional하게 설명만 진행 예정 / 개별적으로 심도있게 더 보자 !
        - Oracle에 "PKNUSB" / "pknu_p@ss" 로 ID, PW설정
            ```shell
            > sqlplus system/password
            SQL> 
            ```
