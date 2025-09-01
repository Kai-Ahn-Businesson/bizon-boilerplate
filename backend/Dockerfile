# 백엔드 Dockerfile
FROM eclipse-temurin:24-jre-alpine

# 작업 디렉토리 설정
WORKDIR /app

# JAR 파일 복사 (빌드 컨텍스트에서)
COPY *.jar app.jar

# 애플리케이션 실행을 위한 사용자 생성
RUN addgroup -g 1001 -S appgroup && \
    adduser -u 1001 -S appuser -G appgroup

# 애플리케이션 파일 소유자 변경
RUN chown appuser:appgroup /app/app.jar

# 사용자 전환
USER appuser

# 포트 노출
EXPOSE 8080

# 헬스 체크
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# 애플리케이션 실행
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]