# study-springboot-2024
_부경대 Java 빅데이터 과정 Spring Boot 학습 리포지토리_
_학습기간 : '24. 6. 12 ~ '24. 7. 4._

- 강의 내용 기록 및 복습용 리포지토리
- 강의의 주된 구성은 Thymeleaf와 JPA를 이용한 Spring Boot 사용
- MyBatis에 대한 부족한 부분은 개인적으로 학습 및 소형 프로젝트를 통해 숙지하기
- _[Spring Boot With MyBatis 개인학습/프로젝트](https://github.com/Koeyh/practice-SpringBoot)_
- _이전 프로젝트에서 사용 해 보지 못한 Spring Security에 대한 복습 철저히 하기_

### 환경 및 도구
- SpringBoot, MyBatis, JPA, Gradle, Thymeleaf, Oracle, H2, DBeaver, Docker

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

## ***[1일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day01.md)***
- 프로젝트 기본 설정 관련

## ***[2,3일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day0203.md)***
- Docker 설치, DB 전환
- MyBatis 학습 관련

## ***[4일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day04.md)***
- JPA 시작
- Bootstrap 적용(뷰)
- Entity 생성 및 관계 설정
  
## ***[5일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day05.md)***
- jUnit 테스트 경험
- 게시글, 댓글 영역 설정
- Thymeleaf 조건, 반복, 파싱 학습
  
## ***[6일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day06.md)***
- Bootstrap 설치파일 => CDN 전환
- 게시글 등록 설정(필수 입력 데이터 설정)
- @NotBlank 어노테이션 학습

## ***[7일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day07.md)***
- 게시물 페이징 처리
- H2 => Oracle DB Migration
- Spring Security 적용 시작

## ***[8일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day08.md)***
- Spring Security 학습 계속
- 로그인 기능 구현
- 게시물 작성자 표시 추가

## ***[9일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day09.md)***
- 게시물, 댓글 수정/삭제 기능 추가
- 앵커 기능 추가(이전에 있던 곳으로)
- 검색 기능 추가

## ***[10일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day10.md)***
- 검색기능 추가 마무리
- 마크다운, 마크다운 뷰 적용
- Category 분류기능 추가(free, qna 등)

## ***[11일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day11.md)***
- 신규 게시물 new뱃지 생성
- 게시물 조회수 표시 기능 추가
- AWS 연결 설정

## ***[12일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day12.md)***
- AWS 연결 확인
- 비밀번호 변경을 위한 네이버 메일 연결 설정
- Postman 사용하여 테스트 메일 전송 확인

## ***[13일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day13.md)***
- 비밀번호 초기화 목적 메일 발송 기능 추가
- 메일로 전송된 링크로 비밀번호 재설정 기능 적용(추가) 완료

## ***[14일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day14.md)***
- React 적용
- Rest API 적용

## ***[15일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/README/Day15.md)***



## 남은 학습 목표

- Spring boot RestAPI 작업

- 소셜 로그인(카카오, 네이버, 구글) 중 구글 로그인 적용
    - https://console.cloud.google.com/ 구글 클라우드 콜솔
    - 프로젝트 생성
    - OAuth 동의화면 설정
    - 계속 진행..
- 파일업로드 - AWS S3 체크


- 리액트 적용
- 리액트로 프론트엔드 설정
- thymeleaf - 리액트로 변경

- 8080 -> 80 서버
- http -> https 변경	
