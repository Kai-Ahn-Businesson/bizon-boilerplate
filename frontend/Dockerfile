# 프론트엔드 Dockerfile
FROM nginx:alpine

# 빌드된 정적 파일 복사
COPY dist/ /usr/share/nginx/html/

# nginx 설정 파일 복사 (옵션)
# COPY nginx.conf /etc/nginx/nginx.conf

# 포트 노출
EXPOSE 80

# nginx 실행
CMD ["nginx", "-g", "daemon off;"]