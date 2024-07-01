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
