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
