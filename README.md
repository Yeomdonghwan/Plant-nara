# PlantFriends

## 소프트웨어 설계 

## 필요한 소프트웨어
- Java 17
- MySQL 8.0.32
- IntelliJ

## 프로젝트 실행 방법

1. Java를 설치합니다. 이미 설치 되어 있다면 넘어가도 됩니다.
   
   https://yungenie.tistory.com/11 를 참고하여 JDK17을 설치합니다.
   
   (참고: jdk20은 버전이 너무 높아 본 프로젝트를 실행할 수 없습니다.)


2. intelliJ를 설치합니다. 이미 설치 되어 있다면 넘어가도 됩니다.

   
3. 이 프로젝트를 clone 받습니다.
   ```
   git clone https://github.com/Yeomdonghwan/PlantFriends.git
   ```
   
4. MySQL을 다운받고, `test`스키마 생성, `test`사용자 생성 후 모든 권한 부여, connection 생성을 해야합니다.

   설치 및 설정 방법은 https://yeom00.tistory.com/546 를 참고해주세요.

   본 프로젝트는 스키마, 유저네임, 패스워드가 다음과 같이 설정되어 있습니다.(패스워드는 공란) 
   ```
   스키마: test 
   username: test
   password:
   ``` 
   변경이 필요하다면, application.yml과 login패키지의 DatabaseConfig를 수정해야합니다.
   ```
    url: jdbc:mysql://localhost:3306/{schema name}?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8&useSSL=true
    username: {username}
    password: {userpassword}
   ```
   (비밀번호를 설정하지 않았다면, 비워두면 됩니다.)

   또한, MySQL버전이 8.0.32가 아니라면, build.gradle파일에서 아래 코드를 찾아 수정해야합니다.

   `runtimeOnly 'mysql:mysql-connector-java:{버전}'`



5. 인텔리제이에서 이 프로젝트를 Open하고, PlantsCafeApplication을 실행합니다.
6. 브라우저 주소창에 `localhost:8080/` 을 입력하면 식물친구들 메인 화면이 나옵니다.  
