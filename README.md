# daily_allowance ( 매일 용돈 받기 )

## 개요
- 매일 랜덤한 금액의 용돈을 받은 시스템 구현
- 미션을 통한 추가 랜덤 용돈 지급
  
---

## 기술
- Spring Boot 3.2.4
- Java 17
- H2 Database ( Embedded mode )
- Spring Data JDBC ( Driver, Datasource ... )
- MyBatis ( Object Mapper )
- Swaager ( API Docs )

- 추가 예정
 - Spring Batch [ 배치 ]
 - Quartz [ 배치 작업에 대한 멀티 애플리케이션 환경에서 클러스터링 및 스케줄링 ]
 - Redis 
 - Spring Security [ 접속 사용자에 따른 계정 권한 부여 ] 

---

## 링크
- [ daily_allowance Application ]( http://localhost:8080/ )
- [ Swagger Doc ]( http://localhost:8080/swagger-ui/index.html )
- [ H2 Console ]( http://localhost:8080/h2 )


## 기능

### 데일리 용돈 받기
- [x] 데일리 용돈 지급
  - [x] 데일리 용돈 받기 중복 확인 - 중복 참여는 예외 발생 ( 하루에 한번만 신청 가능 )
    - 정상 Case
        - [x] 데일리 용돈 지급 이력 등록 
        - [x] 데일리 용돈 지급 히스토리 등록
        - [x] 지급 금액 검증 ( 0원 이하 음수 금액의 경우 지급 불가 )
            - 정상 Case
                - [x] 수신 모듈 호출 ( 주석 임의 진행 )
                - [x] 지급 완료 처리
            - 예외 Case
                - [x] 지급 내역 히스토리 예외 등록 ( 금액 0원 이하 이슈 )
    - 예외 Case
        - [x] 지급 내역 히스토리 예외 등록 ( 중복 참여 이슈 )

### 미션
- [x] 미션 지급
    - [x] 미션 중복 확인 - 중복 참여는 예외 발생 ( 미션별 하루에 한번만 신청 가능 )
    - 정상 Case
        - [x] 미션 지급 이력 등록
        - [x] 미션 지급 히스토리 등록
        - [x] 미션 운영 기간 및 미션 금액 검증
            - 정상 Case
                - [x] 수신 모듈 호출 ( 주석 임의 진행 )
                - [x] 지급 완료 처리
            - 예외 Case
                - [x] 지급 내역 히스토리 예외 등록 ( 미션 미운영 기간, 미션 지급 금액 불일치 )
    - 예외 Case
        - [x] 지급 내역 히스토리 예외 등록 ( 중복 참여 이슈 )

### 용돈 지급 내역
- [x] 용돈 혜택 내역 조회
    - [x] 월별 혜택 조회

### 재처리 배치 프로그램
- [x] 재처리 배치
    - [x] 재처리 대상 조회 ( 지급 실패 건 : 기지급 건에 대해서는 제외 )
