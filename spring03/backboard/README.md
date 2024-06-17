# Spring Boot(3.3.0) With JPA BackEnd Part
'24. 6. 17. ~

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
### H2 console 접근
  - localhost:8080/h2-console
  ![h2콘솔](https://github.com/Koeyh/study-springboot-2024/assets/156414715/3dc25e86-c2f2-4c30-819c-281f9254fa9a)
  
### H2 생성 위치
  ![h2DB 생성](https://github.com/Koeyh/study-springboot-2024/assets/156414715/d4ea54c1-6093-4852-bbde-c532c774382e)

