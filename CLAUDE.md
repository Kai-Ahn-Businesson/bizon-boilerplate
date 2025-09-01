# CLAUDE.md

이 파일은 이 저장소에서 작업할 때 Claude Code (claude.ai/code)에 대한 가이드를 제공합니다.

## 프로젝트 개요

풀스택 애플리케이션:
- **프론트엔드**: React + TypeScript + Vite
- **백엔드**: Spring Boot + Kotlin

## 개발 명령어

### 프론트엔드 (`/frontend` 디렉토리)

```bash
# 의존성 설치
yarn install

# 개발 서버 실행
yarn dev

# 테스트 실행
yarn test

# 프로덕션 빌드
yarn build

# 코드 린트
yarn lint

# 프로덕션 빌드 미리보기
yarn preview
```

### 백엔드 (`/backend` 디렉토리)

```bash
# 프로젝트 빌드
./gradlew build

# 애플리케이션 실행
./gradlew bootRun

# 테스트 실행
./gradlew test

# 빌드 결과물 정리
./gradlew clean
```

## 아키텍처 개요

### 프론트엔드 구조
- **프레임워크**: React 19 + TypeScript
- **빌드 도구**: Vite 7 + React 플러그인
- **테스팅**: Vitest + Testing Library
- **린팅**: ESLint 9 + TypeScript 지원
- **테스트 설정**: `vite.config.ts`에서 jsdom 환경으로 구성

### 백엔드 구조
- **프레임워크**: Spring Boot 3.x + Kotlin
- **빌드 도구**: Gradle + Kotlin DSL
- **Java 버전**: Java 24
- **Kotlin 버전**: 2.2
- **데이터베이스**: 
  - 프로덕션: PostgreSQL
  - 개발 및 테스트: H2
- **주요 의존성**:
  - Spring Web, Actuator, Batch, HATEOAS
  - Spring Data JPA and REST
  - MapStruct (객체 매핑)
  - Jackson (JSON 처리)

## 테스트 가이드라인

### 프론트엔드 테스트
`.cursorrules`에 문서화된 특정 규칙을 따르는 Vitest 사용:
- 핵심 비즈니스 로직과 유틸리티 함수에 집중
- `vi.mock()`을 사용하여 import 전에 의존성 모킹
- 유효한 입력, 무효한 입력, 엣지 케이스 테스트
- describe 블록으로 관련 테스트 그룹화
- 파일당 3-5개의 집중된 테스트로 제한

### 백엔드 테스트
- JUnit 5 + Spring Boot Test 사용
- 아키텍처 테스트용 ArchUnit
- 격리된 테스트 환경을 위한 H2 데이터베이스

## 주요 설정 파일

- 프론트엔드: `frontend/package.json`, `frontend/vite.config.ts`
- 백엔드: `backend/build.gradle.kts`, `backend/src/main/resources/application.properties`
- 테스트: `frontend/.cursorrules`에 상세한 테스트 가이드라인 포함
- frontend에서 yarn 등의 명령어를 수행할 때는 
`cd frontend && source ./env-24.7.0/bin/activate`로
가상환경을 활성화 하고 수행해줘