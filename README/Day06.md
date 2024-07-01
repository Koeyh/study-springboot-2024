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
