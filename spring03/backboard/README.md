# Spring Boot(3.3.0) With JPA BackEnd Part

#### H2 DB 생성
- 의존성 주입
```java
// build.gradle
runtimeOnly 'com.h2database:h2'
```
- H2 설정
```java
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:~/local
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```
- H2 console 접근
  - localhost:8080/h2-console
  - 이미지
- H2 생성 위치
  - ![image](https://github.com/Koeyh/study-springboot-2024/assets/156414715/4619eb58-56ab-4fe4-a0f9-0916249c813a)
