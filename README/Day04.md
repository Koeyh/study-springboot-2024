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
