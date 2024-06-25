# study-springboot-2024
부경대 Java 빅데이터 과정 Spring Boot 학습 리포지토리
- 강의 내용 기록 및 복습용 리포지토리
- 강의의 주된 구성은 Thymeleaf와 JPA를 이용한 Spring Boot 사용
- MyBatis에 대한 부족한 부분은 개인적으로 학습 및 소형 프로젝트를 통해 숙지하기
- _[Spring Boot With MyBatis 개인학습/프로젝트](https://github.com/Koeyh/practice-SpringBoot)_

### 환경 및 도구
- SpringBoot, MyBatis, JPA, Gradle, Thymeleaf, Oracle, DBeaver, Docker

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

## [1일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day01.md)
- 프로젝트 기본 설정 관련

## [2,3일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day0203.md)
- Docker 설치, DB 전환
- MyBatis 학습 관련

## [4일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day04.md)
- JPA 시작
- Bootstrap 적용(뷰)
- Entity 생성 및 관계 설정
  
## [5일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day05.md)
- jUnit 테스트 경험
- 게시글, 댓글 영역 설정
- Thymeleaf 조건, 반복, 파싱 학습
  
## [6일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day06.md)
- Bootstrap 설치파일 => CDN 전환
- 게시글 등록 설정(필수 입력 데이터 설정)
- @NotBlank 어노테이션 학습

## [7일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day07.md)
- 게시물 페이징 처리
- H2 => Oracle DB Migration
- Spring Security 적용 시작

## [8일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day08.md)
- Spring Security 학습 계속
- 로그인 기능 구현
- 게시물 작성자 표시 추가

## [9일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day09.md)
- 게시물, 댓글 수정/삭제 기능 추가
- 앵커 기능 추가(이전에 있던 곳으로)
- 검색 기능 추가

## [10일차 학습](https://github.com/Koeyh/study-springboot-2024/blob/main/Day10.md)
- 진행중









