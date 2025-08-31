# bizon-boilerplate
bizon-boilerplate test

# fe nodeenv
```bash
nodeenv --node=24.7.0 env-24.7.0
source ./env-24.7.0/bin/activate
# venv 내부 전역설치
npm install -g yarn
```
# fe 대화형 진행: `yarn create vite`
# 바로 생성
```bash
yarn create vite frontend --template react-ts
```


# Vitest
## Vitest 및 관련 라이브러리 설치
frontend에서 다음 명령어로 Vitest와 React 테스트에 필요한 라이브러리를 설치합니다.
```bash
yarn add -D vitest jsdom @testing-library/react @testing-library/user-event @testing-library/jest-dom
```
 
# 바로 생성
```bash
yarn dev
```