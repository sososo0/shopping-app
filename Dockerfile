# 사용할 베이스 이미지
FROM openjdk:17-jdk-slim

# 패키지 설치 및 Gradle 설치
RUN apt-get update && \
    apt-get install -y gradle && \
    apt-get clean

# 작업 디렉토리 설정
WORKDIR /app

# gradle 초기화
RUN gradle init

# gradle wrapper를 프로젝트에 추가
RUN gradle wrapper

# gradlew를 이용한 프로젝트 필드
RUN chmod +x gradlew

# gradlew를 이용한 프로젝트 필드
RUN ./gradlew clean build

# 애플리케이션 JAR 파일 복사
COPY build/libs/myselectshop-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
