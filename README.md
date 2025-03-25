emind 프로젝트 (뉴스 피드 서비스)

프로젝트 개요

프로젝트 설명
Remind 프로젝트는 사용자가 다른 사용자를 팔로우하고, 팔로우한 사용자가 작성한 게시물을 조회할 수 있는 뉴스 피드 서비스 입니다.  
이 프로젝트는 단순한 CRUD 기능을 넘어서, 사용자 간의 관계 형성(Follow/Unfollow) 및 뉴스 피드 조회 기능을 구현하는 것을 목표로 합니다.  
또한, 사용자 인증은 JWT(Json Web Token)를 사용하여 보안성을 강화하였습니다.  


프로젝트 목표
1. 회원 관리 시스템 구현
   - 회원가입, 로그인 기능 제공 (JWT 기반 인증 시스템 적용)
   - 프로필 조회 및 수정 기능 제공
   - 비밀번호 변경 기능 제공

2. 게시물 관리 시스템 구현
   - 게시물 작성, 조회, 수정, 삭제 기능 제공
   - 사용자 자신의 게시물 뿐만 아니라, 팔로우한 사용자의 게시물도 조회 가능

   3.팔로우/언팔로우 기능 구현 
   - 특정 사용자를 팔로우하거나 언팔로우하는 기능 제공
   - 팔로우한 사용자들의 최신 게시물을 뉴스 피드 형식으로 조회 가능

4. API
   - Postman을 이용한 API 테스트 결과 검증

---

기술 스택 및 라이브러리


**프로젝트 구조**

# 회원가입 및 로그인 관련 기능 # 댓글 관련 기능 # 팔로우/언팔로우 기능 # 좋아요 기능 # 게시물 CRUD 기능 # 사용자 정보 관리

주요 기능

1.사용자 인증 (회원가입, 로그인, 인증 시스템)
- 회원가입 API: `/auth/register` (POST)
- 로그인 API: `/auth/login` (POST)
- JWT 토큰 발급 및 검증

2. 프로필 관리
- 프로필 조회 API: `/users/{userId}` (GET)
- 프로필 수정 API: `/users/{userId}` (PUT)
- 비밀번호 변경 API: `/users/{userId}/password` (PATCH)

3. 게시물 관리
- 게시물 작성 API: `/posts/{userId}` (POST)
- 게시물 조회 API: `/posts/{postId}` (GET)
- 게시물 수정 API: `/posts/{userId}/{postId}` (PUT)
- 게시물 삭제 API: `/posts/{userId}/{postId}` (DELETE)

4.팔로우/언팔로우 기능
- 팔로우 API: `/follows/{followerId}/follow/{followeeId}` (POST)
- 언팔로우 API: `/follows/{followerId}/unfollow/{followeeId}` (DELETE)
- 팔로우한 사용자의 게시물 조회 API: `/posts/{userId}/feed` (GET)

5.좋아요 기능 (추후 추가 예정)

프로젝트 설정 (MySQL 연동)

application.properties 설정
properties
spring.datasource.url=jdbc:mysql://localhost:3306/remind
spring.datasource.username=root
spring.datasource.password=비밀번호
spring.jpa.hibernate.ddl-auto=update
테이블 생성 (MySQL)
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(255)
);

CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    content TEXT,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES users(id)
);
회원가입 ![image](https://github.com/user-attachments/assets/502ca092-efca-4f27-bda2-348f874a0eef)
로그인 ![image](https://github.com/user-attachments/assets/2e7db88a-20ee-44ff-8013-a16050cc149b)
프로필 조회 ![image](https://github.com/user-attachments/assets/02ceb4d2-e56f-408a-80cf-8a781106fb6e)
프로필 수정 ![image](https://github.com/user-attachments/assets/0a6ada9f-8ba2-4e12-9710-bf43e1303098)
비밀번호 수정 ![image](https://github.com/user-attachments/assets/8f8a1f9b-cf37-46ba-9d50-901010a814a0)
게시물 생성 ![image](https://github.com/user-attachments/assets/74b9ffb8-9236-4bfa-b11a-8ad22048be11)
게시물 수정 ![image](https://github.com/user-attachments/assets/7e8d763a-83db-44a3-9c1b-58d574d0265a)
게시물 조회 ![image](https://github.com/user-attachments/assets/7b0f6d10-20af-48e5-b523-446921549f4b)
게시물 삭제 ![image](https://github.com/user-attachments/assets/ba68f452-9ba5-49cd-9fc4-9c93e6d03e20)
팔로우 ![image](https://github.com/user-attachments/assets/935abb55-e4a6-47bb-ac30-bd474b88d2e9)
언팔로우 ![image](https://github.com/user-attachments/assets/ade6ac66-7664-4295-8adf-f1b1515692ef)
팔로우 사용자 게시물 조회 ![image](https://github.com/user-attachments/assets/2142478a-13b9-445f-a6a1-7975acf1f947)
댓글 작성 ![image](https://github.com/user-attachments/assets/2d12ec15-6853-4987-92df-313f05579b84)
댓글 조회 ![image](https://github.com/user-attachments/assets/3fd248a5-0793-4f67-aad1-ffc14086ca06)
댓글 수정 ![image](https://github.com/user-attachments/assets/91b27257-01a2-43c2-aff1-b77b45d100c0)
댓글 삭제 ![image](https://github.com/user-attachments/assets/53b7cbf4-6ec5-45a9-8077-38a670674b50)
좋아요 추가 ![image](https://github.com/user-attachments/assets/5f5d1663-8a91-4545-9186-31fc92c10a19)
좋아요 취소 ![image](https://github.com/user-attachments/assets/a7db2b7e-eb83-4d57-87e7-081965b4721f)
좋아요 많은 순 정렬![image](https://github.com/user-attachments/assets/dd2f7c03-551f-4877-ba61-47077e3bc70a)
기간별 검색 기능 ![image](https://github.com/user-attachments/assets/ae9ff451-5e54-46b1-ac7a-c7a088a315cb)




