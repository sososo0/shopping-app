# MySelectShop 

MySelectShop은 키워드로 상품을 검색하고 관심 상품을 등록할 수 있습니다.
- 매일 새벽 1시에 최저가 업데이트를 실행하며, 설정한 희망 최저가보다 실제 최저가가 낮다면 최저가 태그가 붙습니다.

## System Overview 

- 현재 프로젝트는 AWS EC2 인스턴스에서 Docker 컨테이너로 Spring Boot 웹 애플리케이션을 실행하고 있습니다. 
- 데이터베이스는 AWS RDS에서 MySQL로 관리합니다. 
- GitHub Actions를 통해 코드를 관리합니다. 

## ERD 

<img width="710" alt="스크린샷 2024-11-05 오후 2 18 37" src="https://github.com/user-attachments/assets/f2257b56-f0a5-497a-b5e8-4c9c3e9f3ff0">

## How to Start

1. git clone 
    ```
   git clone https://github.com/sososo0/shopping-app.git
   ```
   
<br>

2. docker 설치 
    ```
   sudo apt-get update
   sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
   curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
   sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
   sudo apt-get update
   sudo apt-get install docker-ce
   sudo docker --version
   ```
   
<br>

3. docker-compose 설치
    ```
   sudo curl -L "https://github.com/docker/compose/releases/download/v2.12.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
   sudo chmod +x /usr/local/bin/docker-compose
   docker-compose --version
   ```

> +) docker 관련 오류가 발생할 시
> docker 그룹에 사용자 추가하기 
> ```
> sudo usermod -aG docker $USER
> ```
> 
> 변경사항을 저장하기 위해 docker 로그아웃 후 다시 로그인 

<br>

4. .env 파일 만들기 

- 보안과 유지보수를 용이하게 하기 위해 .env 파일을 생성했습니다. 
- 프로젝트의 루트 디렉토리에 생성합니다.
    ```
    /myselectshop
    │
    ├── .env
    ```
<br>

```
DB_PW=
DB_USER=
JWT_SECRET_KEY=
NAVER_CLIENT_ID=
NAVER_CLIENT_SECRET=
ADMIN_TOKEN=
KAKAO_REST_API_KEY=
DB_HOST=
SERVER_HOST= # http 또는 https로 시작하는 서버 주소를 입력하세요 (예: http://localhost)
```

- template은 위와 같습니다. 

<br>

5. docker-compose up -d 

- Dockerfile이 자동으로 빌드되고 springboot web application의 docker container가 생성되고 실행됩니다.

<br>

## Features

구현된 주요 API 목록은 다음과 같습니다. 

<details>
  <summary><span style="font-size: 1.5em;">Naver</span></summary>
  <div markdown="1">

- 상품 검색

  ```
  GET /api/search?query=검색어
  ```

  </div>
</details>

<br>

<details>
  <summary><span style="font-size: 1.5em;">User</span></summary>
  <div markdown="1">

- 로그인 페이지 호출 
    
  ```
  GET /api/user/login-page
  ```
    
- 회원가입 페이지 호출 
    
  ```
  GET /api/user/signup
  ```
    
- 회원가입 
    
  ```
  POST /api/user/signup
  ```
    
- 회원 정보 요청 
    
  ```
  GET /api/user-info
  ```
    
- 카카오 소셜 로그인 인가 코드 처리 
    
  ```
  GET /api/user/kakao/callback
  ```

  </div>
</details>

<br>

<details>
  <summary><span style="font-size: 1.5em;">Product</span></summary>
  <div markdown="1">

- 관심 상품 조회하기
    
  ```
  GET /api/products
  ```
    
- 관심 상품 등록하기 
    
  ```
  POST /api/products
  ```
    
- 관심 상품의 희망 최저가 업데이트 
    
  ```
  PUT /api/products/{id}
  ```
    
- admin 계정 모든 상품 조회 기능 
    
  ```
  GET /api/admin/products
  ```

  </div>
</details>

<br>

<details>
  <summary><span style="font-size: 1.5em;">Folder</span></summary>
  <div markdown="1">
    
- 폴더 전체 조회 
    
  ```
  GET /api/folders
  ```
    
- 회원 폴더 생성 
    
  ```
  POST /api/folders
  ```
    
- 회원 폴더 조회 
    
  ```
  GET /api/user-folder
  ```
    
- 폴더 추가 
    
  ```
  POST /api/products/{productId}/folder
  ```
    
- 폴더 별 관심상품 조회 
    
  ```
  GET /api/folders/{folderId}/products
  ```

  </div>
</details>

