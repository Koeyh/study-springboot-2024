## 9일차
- Spring Boot JPA 프로젝트 개발 (계속)
    1. 수정, 삭제
        - /entity/Board, Reply.java 수정일자 필드 추가
        - /templates/board/detail.html 수정, 삭제버튼 추가
            - sec:authorize="isAuthenticated()"가 없으면 500 에러 발생
        - 삭제 버튼은 바로 실행되지 않게 javascript 추가
        - /controller/BoardController.java에 modify() GET 메서드 작성
        - /templates/board/create.html 수정(생성, 수정이 같은 페이지에서 진행)
            - form 태그 내 th:action부분 삭제 (이유는 다음과 같음)
            - Get이 /board/create로 들어가면 post도 /board/create로 실행되고
            - /board/modify/{bno}로 들어가면 post도 /board/modify로 실행되기 떄문에 별도 action 지정 불필요
        - /service/BoardService.java에 수정 관련된 메서드 추가작성
        - /controller/BoardController.java에 modify() POST 메서드 작성
            - html에는 BoardForm 객체 값이 들어있음. 컨트롤러에서 받아서 Board 객체를 다시 만들어 서비스로 전달

        - /service/BoardService.java에 삭제관련 메서드 추가
        - /controller/BoardController.java에 delete() GET 메서드 작성

        - /templates/board/detail.html 댓글 수정, 삭제버튼 추가
        - /service/ReplyService.java에 댓글 수정, 삭제 관련 메서드 추가
        - /controller/ReplyController.java modify GET, POST 메서드 작성, 삭제 GET 메서드 작성
        - /templates/reply/modify.html 생성, 작성

        - /templates/board/detail.html에 게시글, 댓글 수정날짜 표시


        
    2. 앵커 기능 _(이전 프로젝트에서 구현하지 못한 기능)_
        - 추가, 수정, 삭제 시 이전 자신의 위치로 되돌아가는 기능
        - /template/board/detail.html 댓글마다 앵커링 추가
        - /controller/ReplyController.java에 modify Post매핑 부분 return에 앵커링 추가
        - /service/ReplyService.java 생성 메서드 void -> Reply 변경 
        - /controller/ReplyController.java create Post 메서드를 변경

        - /controller/BoardController.java에 detail() 메서드 수정

    3. 검색 기능
        - /service/BoardService.java에 search() 메서드 추가
        - /repository/BoardRepository.java 메서드 추가
        - /service/BoardService.java에 getList() 메서드 추가생성
        - /controller/BoardController.java에 list()메서드 추가생성
        - /templates/board/list.html에 검색창 추가, searchForm 폼 영역 추가, **페이징 영역 수정, Javascript 추가**
