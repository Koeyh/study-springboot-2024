## ***15일차***
- Spring Boot React 연동 프로젝트 개발 계속
1. React 프로젝트 생성
    - 터미널 >> /spring03 으로 이동
    - > npx create-react-app frontboard

2. Spring Boot / React 같이 개발할 때
    - Spring Boot 웹서버 실행
    - React 실행 프론트웹서버 실행

3. React 라이브러리 설치, npm
    - React용 Bootstrap 설치
    - > npm install react-bootstrap bootstrap
    - **TIP : npm audix fix --force는 절대 쓰지 말것**
    - > npm install axios -> REST API 통신 라이브러리
    - > npm install react-router-dom -> React 화면 네비게이션
    - > npm install react-js-pagination -> 리액트 페이징 처리

4. frontboard 개발 시작
    - App.js에서 logo.svg삭제, react-router-dom으로 Routes, Route 사용
    - index.js에 reportWebVitals() 삭제
    - index.js, <React.StrictMode> 삭제 혹은 주석처리
    - /src/layout/Header.js, Footer.js 생성
    - /src/routes/Home.js, BoardList.js, QnaList.js, Login.js 생성
    - App.js에 Route될 화면 추가
    - Header.js에 react-router-dom 추가, Link, userNavigate함수 사용

5. backboard RestAPI 추가
    - /restcontroller/RestBoardController.java 생성, BoardController에 있는 메서드 복사
    - ***(문제!)Spring Boot와 Rest API 간 리턴 데이터 차이로 인해 100% 호환 되지 않음***
    - ***(문제!) Spring Boot에서 만든 Entity는 Board와 Reply 등의 OneToMany / ManyToOne 관계가 JSON으로 변환할 때 문제 발생***
    - /Entity를 그대로 사용하지 말고, RestAPI에서는 다른 클래스를 만들어야 한다.
    - /dto/BoardDto.java 생성
    - /dto/ReplyDto.java 생ㅅ성
    - /RestBoardController.java의 getList()를 Board Entity -> BoardDto로 변경
    - /security/SecurityConfig.java에 _CORS 설정 추가_

6. frontboard 개발 계속
    - BoardList.js에 axios 관련 RestAPI 호출
    - 테이블 내용을 boardList.map()으로 10개의 리스트(페이징 된 것들 중 첫번째 페이지 항목) 조회

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/react004.png" width="500">
