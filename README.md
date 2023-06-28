
# 개발 상태 : [게시판 개발 완료 , 댓글 개발 완료, SearchType keyword(검색) 개발 진행중 ]

# boardReplyMvc
- 프로젝트 유형: Board 게시판 , Reply 댓글, 페이지네이션 연습 
- 목표: JavaScript, Thymeleaf, Spring Boot 를 활용하여 페이지네이션, 검색 기능이 포함된 Board 애플리케이션 개발
- 목표: 개발의 순서 정립 DataBase 설계 
- 목표: 개발의 순서 정립 MapperInterface 개발 => Mapper.xml 개발 => MapperTestCode 작성 => ServiceInterface 개발 => ServiceImpl 개발 => ServiceTestCode 작성
- 목표: 개발의 순서 정립 controller 개발 => html 개발 => 페이징 개발 => 댓글 개발 

## Spring Boot 프로젝트 설정

### 빌드 플러그인
- `java`: Java 플러그인을 사용하여 프로젝트에 Java 컴파일 기능을 추가합니다.
- `org.springframework.boot`: Spring Boot 기능을 프로젝트에 추가합니다. 버전은 3.1.1으로 설정됩니다.
- `io.spring.dependency-management`: Spring의 의존성 관리 기능을 추가합니다. 버전은 1.1.0입니다.

### 프로젝트 정보
- `group`: 프로젝트 그룹 ID를 'com.board'로 설정합니다.
- `version`: 프로젝트 버전을 '0.0.1-SNAPSHOT'으로 설정합니다.
- `sourceCompatibility`: 프로젝트 Java 버전을 17로 설정합니다.

### 태스크 설정
- `test`: JUnit Platform을 사용하도록 설정합니다.

### 저장소
- Maven 중앙 저장소에서 의존성을 가져옵니다.

## 프로젝트 사용 방법
1. 프로젝트를 빌드합니다.
2. Run Application을 실행합니다.
3. 웹 브라우저에서 `/board/list`로 이동합니다.

## 부트스트랩 사용
- [AdminSB](https://startbootstrap.com/theme/sb-admin-2) 템플릿을 사용합니다.

## boardReplyMvc: boardReplyMvc 게시판, 댓글, 페이지네이션 소개 
- 데이터 규모 
- 이 토이 프로젝트에서는 약 3000만건의 데이터를 사용하여 페이지네이션 기능을 테스트하고 최적화하였습니다. 
- 이를 통해 대규모 데이터셋에 대해 어플리케이션의 성능을 측정하고, 사용자에게 빠른 응답 시간을 제공하는 방법을 연구하였습니다.

### 주요 기능
- Board 항목 생성, 조회, 수정, 삭제
- 페이지네이션 지원
- Board 항목 title, content, writer 검색 지원 
- Board 항목 필터링 및 정렬 기능
- Bootstrap을 이용한 반응형 웹 디자인

- Reply 항목 생성, 조회, 수정, 삭제
- 페이지네이션 지원

## 시작하기

### 사전 요구 사항
- Java 17
- MySQL
- 웹 브라우저

### 데이터베이스 설정
프로젝트를 실행하기 전에 MySQL 데이터베이스를 설정하고 `application.properties` 파일에서 데이터베이스 연결 속성을 구성하세요.

### 빌드 및 실행 방법
1. 이 저장소를 복제하거나 다운로드합니다: `git clone [repository_url]`
2. 프로젝트 디렉토리로 이동합니다: `cd boardReplyMvc`
3. 프로젝트를 빌드합니다: `./gradlew build`
4. 애플리케이션을 실행합니다: `./gradlew bootRun`
5. 웹 브라우저에서 애플리케이션에 접속합니다: `http://localhost:8084/board/list`

## 부트스트랩 사용
이 프로젝트는 부트스트랩 템플릿을 사용하여 사용자 인터페이스를 구성합니다. 템플릿에 대한 자세한 정보는 [AdminSB](https://startbootstrap.com/theme/sb-admin-2)를 참조하세요.

## 개발자 정보
- 이름: [권성준]
- 이메일: [thistrik@gmail.com]
- 이메일: [thistrik@naver.com]

## 피드백 기여 및 지원 기타 문의 
- 프로젝트에 대한 의견과 피드백은 언제나 환영합니다! 만약 프로젝트에 대한 개선 사항이나 버그 제보, 그 외 다른 의견이 있으시면, 언제든지 연락해주시기 바랍니다.

## 데이터베이스 테이블 정보

### Board 테이블 (`tbl_board`)

| 컬럼명   | 데이터 타입     | 설명                                       |
|----------|----------------|--------------------------------------------|
| tno      | INT            | Board 항목의 고유 식별자 (Primary Key, 자동 증가) |
| title    | VARCHAR(500)   | Board 항목의 제목 (null이 아님)                |
| content  | VARCHAR(1000)  | Board 항목의 상세 내용 (null이 아님)           |
| writer   | VARCHAR(100)   | Board 항목을 생성한 사용자명 (null이 아님)     |
| registDate | TIMESTAMP        | Board 의 생성 날짜          |
| updateDate  | TIMESTAMP           | Board 의 업데이트 날짜                        |
| replyCnt | INT | Board 의 댓글 개수 |

### Reply 테이블 (`tbl_reply`)
| 컬럼명 | 데이터 타입 | 설명 |
| --- | --- | --- |
| rno | INT | 답글 항목의 고유 식별자 (Primary Key, 자동 증가) |
| tno | VARCHAR(500) | 관련 Board 항목의 고유 식별자 |
| reply | VARCHAR(1000) | 답글의 내용 |
| replyer | VARCHAR(100) | 답글을 작성한 사용자명 |
| replyDate | TIMESTMAP | 답글이 작성된 날짜와 시간 (기본값은 현재 시간) |
| modifyDate | TIMESTMAP | 답글이 수정된 날짜와 시간 (기본값은 현재 시간) |
| gno | INT | 그룹 번호 (기본값 0) |
|isDeleted| TINYINT | 댓글.대댓글이 삭제되었는지 확인하고 업데이트|


SQL 스키마:
```sql

CREATE TABLE tbl_board (
	tno INT AUTO_INCREMENT PRIMARY KEY,
	title VARCHAR(500) NOT NULL,
	content VARCHAR(1000) NOT NULL,
	writer VARCHAR(100) NOT NULL,
	registDate TIMESTAMP DEFAULT NOW(),
	updateDate TIMESTAMP DEFAULT NOW(),
	replyCnt int DEFAULT 0
)
;

CREATE TABLE tbl_reply (
    rno INT AUTO_INCREMENT PRIMARY KEY,
    tno INT NOT NULL,
    reply VARCHAR(1000) NOT NULL,
    replyer VARCHAR(100) NOT NULL,
    replyDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifyDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    gno INT DEFAULT 0,
    isDeleted TINYINT DEFAULT 0,
    FOREIGN KEY (tno) REFERENCES tbl_board(tno) ON DELETE CASCADE
);

```
