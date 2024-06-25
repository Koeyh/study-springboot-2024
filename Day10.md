## 10일차
- Spring Boot JPA 프로젝트 개발 (계속)
    1. 검색기능(계속) - JPA Query
        - @Query 어노테이션으로 직접 쿼리를 작성
        - ***@Query는 단순 쿼리가 아니기 때문에 JpaRepository가 자동으로 만들어 줄 수 없을 때 사용***
        - ***DB의 표준 Query와는 상이함(Java의 Entity와 일치)***
        - /repository/BoardRepository.java의 findAllByKeyword() 메서드 추가
        - JPA Query @Query(" ... ")에 작성
        - /service/BoardService.java getList()수정

    2. 마크다운 적용, 마크다운 에디터 추가
        - Wysiwyg 에디터 - CKEditor(https://ckeditor.com/), TinyMCE
        - simplemde(https://simplemde.com/) View On Github 클릭 >> CDN 링크 복사, layout.html 링크 추가
        - create.html, textarea id content를 simplemde로 변환하는 js 추가

        - (설정) build.gradle 마크다운 뷰 의존성 추가
        - /common/CommonUtil.java 생성
        - /templates/board/detail.html 마크다운 뷰어 적용
            - th:text를 ***th:utext로 변경***
            - ```java
                <div th:utext="${@commonUtil.markdown( board.content )}" class="card-text"></div>
    3 카테고리 추가(게시판, QnA , 공지사항)
    - 비밀번호 찾기, 비밀번호 변경
    - 조회수 추가

    - React 적용 고민
    - 리액트로 프론트엔드 설정
    - 타임리프 - 리액트로 변경
    - 스프링부트 RestAPI 작업 

    - AWS 라이트세일
    - 서버 접속 프로그램 설정
    - 웹 서버 배포
    - 8080 -> 80 서버로 변경
    - http -> https 변경
