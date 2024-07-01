## ***14일차***

- React 개요
    - 서버 => 백엔드 (SSL)
    - 클라이언트 사이드 => 프론트엔드 (CSL)
    - 프론트 : html + css + js + php ... 다양함
    - js만 가지고 프론트엔드 구성 목표
    - 했으나 css까지는 필요함
    - Javascript + css : React라고 우선 생각하자
    - Facebook에서 자사 웹페이지 개선을 위해 개발 시작
    - 리액트는 기본적으로 SPA(Single Page Application)을 목적으로 함
        - 페이징 된 게시판과 그다지 적합하지는 않은듯
    - Node.js 서버사이드 js를 사용해서 서버를 동작
    - 패키지 매니저 종류 : npm, chocolatey, yarn, .... 다수 (본인이 필요한 것만 습득하자)


- React 개발 환경 설정
    1. Node.js 설치
        - https://nodejs.org/en/ => Download Node.js LTS 클릭
        - 설치 후 콘솔에서 node --version 으로 설치 확인. 현재 v20.15.0
    2. React 패키지 설치
        - npm uninstall -g create-react-app
        - npm install -g create-react-app

    3. React 프로젝트 초기화
        - VS Code에서 터미널 오픈
        - npx create-react-app "프로젝트명"

    4. React 실행
        - 콘솔에서 위에서 만든 프로젝트앱 이름까지 진입 (현재 basic-app)
        - npm start
        - 웹브라우저 http://localhost:3000 서버 확인
        - node가 3000 포타로 웹서버를 실행
        - 웹 서버가 실행된 상태에서 개발하는 것이 가장 좋음
        - index.html(jsp, php)가 가장 처음 화면. 현재 프로젝트에서 App.js가 메인 개발 부분
            - index.js -> app.js -> index.html....
        - App.js부터 개발 시작


- React 기본구조 및 개발방법
    1. Github .gitignore에 react(node)관련 설정내용 추가
    2. ***Github에 .gitignore 먼저 commit & push 실행***
    3. src/App.css, App.js, index.js 파일만 신경쓰면 된다 !
    4. javascript이기 떄문에 js위주로 개발

- React 기초 공부
    1. html의 태그처럼 개발자가 새로운 요소(객체)를 생성할 수 있음
    ```jsx
    function CustomButton() {   // CustomButton 이라는 객체 생성
        return (
        <button>MyButton</button>
        );
    }
    ```

    2. /component/CustomButton.js 생성, 위 소스를 옮김
        - 같은 파일이 아닌 곳에 객체를 생성하면
        - 가져와 쓰기 위해서 보내주는 측에서는 "export default 객체이름"을 필수적으로 작성
        - 받아오는 측에서는 import 파일, 폴더명 from 경로를 작성 해 줘야한다.
    
    3. React 문법은 JSX라 한다. 일반 JS와 약간의 차이가 있음
        - className은 JSX에만 존재하는 속성
        - HTML에 있던 class는 JSX에서 className으로 변경(필수!!)
        - 인라인으로 style 사용 시 CSS 명칭이 다름
        - 대신, *.css 파일로 작업할 때는 기존 그대로 사용해도 무방함
        - JSX 문법에는 모든 요소는 상위 태그 하나에 속해야 한다
            - ```jsx
                function CustomButton() {   // 이게 되네..
                    return (
                        <>
                            <button>MyButton</button>
                            <button>MyButton2</button>
                        </>
                    );
                }
              ```
        - ***[HTML -> JSX 문법 변환 참고](https://transform.tools/html-to-jsx)***
    
    4. 데이터 화면에 표시
        - 변수 생성 시 const를 많이 사용
        - 변수 사용시 중괄호({}) 사이에 입력
        - CSS를 *.css 파일에 작성할 때는 html에서 사용할때와 동일
            ex. border-radius: 50%
        - JSX에서 사용 할 떄는 다음과 같이 사용
            - ex. borderRadius: '50%'
        - React에서 css를 사용할 때는 *.css파일로 작업할 것

        
    5. 조건부 랜더링
    ```jsx
    function CustomButton() {

        let isLoggedIn = false; // 로그인 여부 설정
        let content;

        if(isLoggedIn) {
            content = <button>Log Out</button>
        } else {
            content = <button>Log In</button>
        }

        return (
            <>
                {content}
            </>
        );
    }
    ```
    - 혹은
    ```jsx
    {
        isLoggedIn ? (
            <button>Log Out</button>
        ) : (
            <button>Log In</button>
        )
    }
    ```

    6. 목록 표시
        - for, map() 함수를 많이 사용함
        - map()을 쓰면 for문보다 짧게 구현 가능
        - 각 child 요소마다 key 속성이 필요! (없으면 경고 발생)
            
        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/react001.png" width="700">

    7. ***이벤트 핸들링(중요!)***
        - form + onSubmit, tag + onClick
        - 이벤트에 파라미터 전달
        - 파라미터가 필요해서 함수 뒤에 ()를 사용하면, 이벤트 순서에 따라 새로고침 후 자동 실행됨
        - onClick = { () => function() } 과 같은 람다식으로 변경 필요

    8. 컴포넌트 간 데이터 전달
        - props 속성 사용
        - _props.속성명.key명_ 의 형태로 값을 받아옴

    9. 화면 업데이트
        ***- useState : 앱 화면의 상태를 기억하고, 사용하기 위한 함수***
        - import { useState } from 'react' : 필수 ! 

        <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/react003.png" width="700">



    10. Hooks
        - use로 시작하는 함수를 Hooks라고 호칭 (ex. useState, useEffect ..)
        - useState : React 컴포넌트 상태를 추가, 보관
            - 어느 페이지에, 어떻게 작성하느냐에 따라 결과가 달라지는 모습 확인
        - useEffect : 컴포넌트에서 사이드 이펙트 수정할 때
        - 기타 : useContext, useReaducer, useCallback, useRef ....

- React 추가 내용
    1. 리액트 관련 프레임워크
        - Next.js -> 풀스택 React프레임워크
        - Gatsby -> 정적사이트 React 프레임워크
        - React Native -> Android, iOS 멀티플랫폼 모바일 프레임워크

    2. npm 추가 라이브러리 설치
        - > npm install react react-dom

    3. VS Code 확장
        - ES7 + React/Redux/React-Nat 설치
        - VS Code React Refactor : 리팩팅 도구
        - Import Cost : 라이브러리 비용 계산
        - Simple React Snippets
    
    4. React 디버거 (React는 디버깅이 편리하지 못한 편 ??)
        - Chrome, edge 브라우저별로 따로 존재
        - React Developer Tools 개발자 도구 설치
            - chrome -> 우측상단 더보기 -> 확장프로그램 -> react 검색

            <img src="https://raw.githubusercontent.com/Koeyh/study-springboot-2024/main/images/react002.png" width="500">


- Spring Boot React 연동 프로젝트 개발 계속
    1. React 프로젝트 생성
        - 터미널에서 /spring03 이동
        - npx create-react-app frontboard 입력


    2. backboard(Rest API) 적용








            
