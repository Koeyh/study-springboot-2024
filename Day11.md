## ***11일차***
-Spring Boot JPA 프로젝트 개발 계속
    - /controller/MainController.java main()메서드 URL 변경
1. 조회수 표시 기능 추가
    - /entity/Board.java에 조회수 필드 추가
    - /service/BoardService.java에 hitBoard() 메서드 추가
    - /controller/BoardController.java에 detail() 메서드 수정
    - /templates/board/list.html 조회수 컬럼 추가
    - DB를 Oracle 에서 H2로 다시 Miagration


2. (설정)AWS 사용
    - https://aws.amazon.com/ko/ 접속
    - (프리티어) 회원가입 및 로그인
        - 매뉴얼 참조(최근 내용으로 참조)
        - https://blogworks.co.kr/aws-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%EB%A9%94%EB%89%B4%EC%96%BC/
    - (라이트세일) https://lightsail.aws.amazon.com/ 접속
        - 인스턴스 클릭 > 인스턴스 생성 버튼 클릭
        - 리전 서울
        - 인스턴스 이미지 > Linux/Unix
        - 블루프린트 > 운영체제 OS 전용 클릭 > Ubuntu 22.04 LTS
        - 인스턴스 플랜 > 듀얼 스택
        - 크기 선택 > 월별 $12 선택(무료 중 가장 스펙이 좋음)
        - 인스턴스 확인 > 본인이 원하는 이름으로 변경 후 
        - 인스턴스 생성 클릭 !
        - 실행 중 확인 > ⁝ 클릭 > 관리
        - 네트워킹 > 고정 IP연결 > 아이피명 입력 > 생성 
        - IPv4 방화벽 > 규칙 추가 > 8080 추가
        - 계정 > SSH 키 > 기본 키 다운로드(*.pem) > 이름변경(옵션)

    - PuTTY AWS 리눅스서버 연결
        - https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html 64비트 다운로드 및 설치
        - PuTTYgen 실행 > Load 기본키 선택 > Save private key 클릭 > .ppk로 저장
        - PuTTY 실행 
            - Host Name : AWS 고정아이피 입력
            - Connection > SSH > Auth > Credentials : Private Key를 .ppk로 선택
            - Session > Saved Session명 입력 > Save
            - Open 후 콘솔 login as: ubuntu 입력

    - FileZilla로 FTP 연결
        - https://filezilla-project.org/download.php 다운로드
        - 사이트 관리자 열기
            - 새 사이트
            - 프로토콜 : SFTP
            - 호스트 : 고정아이피 입력
            - 로그온 유형 : 키 파일
            - 사용자 : ubuntu
            - 키 파일 : *.ppk 선택
            - 연결
    
    - 설정 변경
        ```shell
        > sudo ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime (한국 시간 변경)
        > hostname
        > sudo hostnamectl set-hostname hugo83
        > sudo reboot (서버 재시작)

        > sudo apt-get update (전체서버 패키지 업데이트)
        > java
        > sudo apt-get install openjdk-21-jdk
            Do you want to continue? [Y/n] y
        > java -version
        openjdk version "21.0.3" 2024-04-16
        OpenJDK Runtime Environment (build 21.0.3+7-Ubuntu-122.04.1)
        OpenJDK 64-Bit Server VM (build 21.0.3+7-Ubuntu-122.04.1, mixed mode, sharing)
        ```
    - VSCode
        - Gradle for java > Tasks > build > bootJar
        - *-SNAPSHOT.jar 생성 확인
    - FileZilla
        - *.jar > AWS로 전송

    - PuTTY 
        ```shell
        > ls 
        ...
        > cd bootserver
        > ls
        backboard-1.0.1-SNAPSHOT.jar
        > java -jar backboard-1.0.1-SNAPSHOT.jar 
        ```
        - sudo java -jar ... 로 실행하면 안됨 !

    - 스프링부트서버 백그라운드 실행 쉘 작성
        - > nano start.sh
            ```shell
            #!/bin/bash

            JAR=backboard-1.0.2-SNAPSHOT.jar
            LOG=/home/ubuntu/bootserver/backbord_log.log

            nohup java -jar $JAR > $LOG 2>&1 &
            ```
        - 파일권한 바꾸기(실행가능)
            ```shell
            > chmod +x start.sh
            ```

        - > nano stop.sh
            ```shell
            #!/bin/bash

            BB_PID=$(ps -ef | grep java | grep backboard | awk '{print $2}')

            if [ -z "$BB_PID" ];
            then
                echo "BACKBOARD is not running"
            else
                kill -9 $BB_PID
                echo "BACKBOARD terminated!"
            fi
            ```
        - 파일권한 바꾸기(실행가능)
            ```shell
            > chmod +x stop.sh
            ```
        
        - 서버실행
