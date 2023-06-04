# PlantFriends

## 소프트웨어 설계 

## 필요한 소프트웨어
- Java 17
- MySQL 8.0.32
- IntelliJ

## 프로젝트 설정
1. 이 프로젝트를 clone 받습니다.
   ```
   git clone https://github.com/Yeomdonghwan/PlantFriends.git
   ```
2. MySQL에서 `test` 데이터베이스를 생성하고, 사용자 `test`를 생성하고 해당 사용자에게 `test` 데이터베이스에 대한 모든 권한을 부여합니다.
3. application.yml 파일에 MySQL 사용자 `test`의 비밀번호를 설정합니다.
   ```
       username: test
       password:
   ```
   비밀번호를 설정하지 않았다면, 비워두면 됩니다.
4. MySQL버전이 8.0.32가 아니라면, build.gradle파일에서 아래 코드를 찾아 수정해야합니다.

   `runtimeOnly 'mysql:mysql-connector-java:버전'`

## 프로젝트 실행 방법
터미널을 열고 프로젝트 루트 디렉토리로 이동한 후, 다음 명령어를 실행합니다:
`./gradlew bootRun`

(또는, PlantsCafeApplication을 실행하면 됩니다.)