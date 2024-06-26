## 11일차
-Spring Boot JPA 프로젝트 개발 계속
    - /controller/MainController.java main()메서드 URL 변경
1. 조회수 추가
    - /entity/Board.java에 조회수 필드 추가
    - /service/BoardService.java에 hitBoard() 메서드 추가
    - /controller/BoardController.java에 detail() 메서드 수정
    - /templates/board/list.html 조회수 컬럼 추가
    - DB를 Oracle 에서 H2로 다시 Miagration



2. AWS 사용
    - https://aws.amazon.com/ko/
    - (프리티어) 회원가입 및 로그인
        - 매뉴얼 참조 (구글 레퍼런스)
    - 라이트세일 접속
        - 인스턴스 클릭 > 인스턴스 생성 버튼 클릭
        - 리전 > 서울
        - 인스턴스 이미지 > Linux/Unix









### 남은 일
- 비밀번호 찾기, 비밀번호 변경
- 소셜 로그인(카카오, 네이버, 구글)
- 파일 업로드 - AWS S3
- React 적용 고민
- 리액트로 프론트엔드 설정
- 타임리프 - 리액트로 변경
- 스프링부트 RestAPI 작업 

- AWS 라이트세일
- 서버 접속 프로그램 설정
- 웹 서버 배포
- 8080 -> 80 서버로 변경
- http -> https 변경