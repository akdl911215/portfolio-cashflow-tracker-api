# Portfolio Cashflow Tracker API

> **Kotlin (Spring Boot) 백엔드 – v0.1**
> 수동 입력/CSV 기반 데이터 + 요청 시 계산(Derived) 대시보드 아키텍처

---

## 1. 프로젝트 목적

여러 계좌·자산의 **보유/가격/배당(현금흐름)** 데이터를 통합하여

* 포트폴리오 총 가치
* 배당 총액 / 배당률
* MoM / YoY 성과
* 자산군·종목별 비중

을 **실사용 가능한 개인용 웹 서비스**로 제공한다.

> ❌ 증권사 로그인 자동화 / 주문 기능 없음
> ✔️ 수동 입력 및 CSV 업로드 기반
> ✔️ v0.1은 스케줄러/배치 없이 운영

---

## 2. 전체 아키텍처 개요

### 핵심 설계 원칙

* **Feature(도메인) 단위 수직 분리(Vertical Slice)**
* 각 feature는 `domain / application / adapter` 구조를 가짐
* 데이터는 *입력값이 기준(Source of Truth)*
* Dashboard는 저장하지 않고 **요청 시 계산(Derived View)**

```
[ Web(API) ]
     ↓
[ application (UseCase / Query) ]
     ↓
[ domain (Rule / Model) ]
     ↓
[ persistence (JPA) ]
```

---

## 3. 디렉토리 구조 (src/main/kotlin 기준)

```
com/yourname/portfolio
├─ PortfolioApplication.kt
├─ config/
├─ common/
└─ feature/
   ├─ account/
   ├─ asset/
   ├─ holding/
   ├─ price/
   ├─ ledger/
   ├─ dashboard/
   └─ import/
```

---

## 4. 루트 및 공통 영역

### 4.1 PortfolioApplication.kt

* Spring Boot 진입점
* 컴포넌트 스캔 기준 패키지

### 4.2 config/

**프레임워크·환경 설정 전용 영역**

* `JacksonConfig` : JSON 직렬화 규칙
* `JpaConfig` : JPA/Auditing 공통 설정
* `WebConfig` : CORS, 인터셉터 등

### 4.3 common/

Feature 전반에서 재사용되는 **횡단 관심사**

#### common/error

* `ErrorCode` : 에러 코드 + HTTP 상태 표준화
* `BusinessException` : 유스케이스 레벨 예외
* `GlobalExceptionHandler` : 공통 에러 응답 포맷

#### common/time

* `ClockProvider` : 시간 의존성 추상화
* 테스트 가능한 날짜/시간 계산을 위해 도입

---

## 5. Feature별 설명

## 5.1 account – 계좌

> 증권사 계좌, Musicow 계좌 등 **계좌 단위 관리**

**domain**

* `Account` : 계좌 엔티티
* `AccountType` : BROKERAGE, MUSICOW 등

**application**

* 계좌 등록/수정/삭제/조회 유스케이스

**adapter**

* Web API (Controller, DTO)
* JPA Persistence

---

## 5.2 asset – 자산(종목)

> 주식, ETF, 채권, Musicow 곡 등 **자산 정의**

* `AssetClass` : STOCK / ETF / BOND / MUSICOW / CASH
* 계좌와 분리된 **자산 카탈로그 역할**

---

## 5.3 holding – 보유 수량

> **계좌(Account) – 자산(Asset)** 연결 엔티티

* 포트폴리오 가치 계산의 핵심 데이터
* `UpsertHoldingUseCase` 로 수량 관리

---

## 5.4 price – 가격 스냅샷

> v0.1 기준 핵심 입력 데이터

* 자동 수집 ❌ / 수동 입력 & CSV 업로드 ✔️
* `(assetId, date)` 기준 유니크 스냅샷

**주요 유스케이스**

* 가격 단건 등록
* CSV 일괄 업로드
* 최신 가격 / 히스토리 조회

---

## 5.5 ledger – 현금흐름 원장

> 배당·입출금·수수료를 **단일 원장 구조로 통합**

* `LedgerType` : DIVIDEND / DEPOSIT / WITHDRAW / FEE
* 배당 총액, 월별 현금흐름의 원천 데이터

---

## 5.6 dashboard – 대시보드 (Derived)

> **저장하지 않고 요청 시 계산**

조합 데이터:

* holding (보유 수량)
* price (가격)
* ledger (현금흐름)

**산출 결과**

* 총 자산 가치
* 자산군/종목 비중
* 배당 요약
* MoM / YoY 성과

> 성능 이슈 발생 시에만 캐시/집계 테이블 도입

---

## 5.7 import – CSV 공통

> CSV 업로드 로직의 중복 제거

* CSV 파싱
* 스키마 검증
* 성공/실패 리포트 생성

Feature별 Import Controller와 조합 가능

---

## 6. resources/

* `application.yml` : 환경 설정
* `db/migration/` : Flyway 도입 시 마이그레이션 SQL

---

## 7. 테스트 전략 (권장 우선순위)

1. Price 스냅샷 중복 방지
2. Dashboard 계산 로직 (MoM / YoY)
3. Ledger 금액 합계/반올림 규칙

---

## 8. 확장 계획 (v1.x)

* 스케줄러 기반 가격 자동 수집
* Dashboard 집계 캐시/저장
* 멀티 유저 지원 + 인증/인가
* 외부 가격 API 연동

---

## 9. 한 줄 요약

> **입력 데이터는 명확하게, 계산 결과는 유연하게.**
> v0.1에서는 단순하지만, 운영과 확장을 견딜 수 있는 구조를 목표로 한다.
