# 사용할 베이스 이미지
FROM openjdk:17-jdk-slim

# 패키지 설치 및 Gradle 설치
RUN apt-get update && \
    apt-get install -y gradle && \
    apt-get clean

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 파일 복사
COPY . .

# gradlew에 실행 권한 부여
RUN chmod +x gradlew

# Gradle 빌드 실행 및 결과 확인
RUN ./gradlew clean build && ls build/libs

# 애플리케이션 JAR 파일 복사
# JAR 파일이 생성된 후 복사
COPY build/libs/myselectshop-0.0.1-SNAPSHOT.jar app.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
