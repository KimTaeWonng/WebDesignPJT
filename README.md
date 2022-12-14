## 📌깃 컨벤션

### 💡 커밋룰

#### Commit 메세지 작성 법

```
Type: 커밋내용 [지라이슈번호]
- Type은 영어로, 그 뒤 커밋 내용은 한글로 적기로 한다.
- ex) FIX : 로그인 에러 수정
```

#### Type 유형

```
FE: 프론트앤드 기능 개발, 코드 수정
BE: 백앤드 기능 개발, 코드 수정
FIX: 버그 수정
DOC: 문서 수정
BUILD: 시스템 또는 외부 종속성에 영향을 미치는 변경사항(npm, yarn 등)
CI: CI 관련 설정
MERGE: 기능 개발 후 브랜치 병합
CHORE: 그 외 자잘한 작업
```

### 💡 브랜치

```
Master: 최종 코드 완성시 merge 한다.
Develope: 각 FE, BE 브랜치에서 작업한 내용을 merge 한다.
FE: 프론트앤드 작업. 세부 기능은 하위 브랜치 생성하여 작업 후 merge 한다.
BE: 백앤드 작업. 세부 기능은 하위 브랜치 생성하여 작업 후 merge 한다.
```

### 💡 브랜치 전략

```
BE or FE-기능
-ex) BE-로그인, FE-로그인 화면 구현
```

## 📌각 문서 링크

[기능명세서, 요구사항 정의서, API명세서 통합본](https://docs.google.com/spreadsheets/d/1Snsq4_rEgos0y4JuI4C1bfJfTSqp9-xIvPWQTEl6qoY/edit#gid=0)

[와이어프레임](https://ovenapp.io/view/M10rj2Ij6jc580rV5fPBZKw8izPXFVDp/INGlZ)

[스토리보드](https://www.figma.com/file/UMAq04A8MwNMbEdVgHQgA2/%EB%94%94%EC%9E%90%EC%9D%B81?node-id=0%3A1)

# ShareSpot 서비스

## 📌 회원관리

### 1) 회원가입

<img width=30% src="https://user-images.githubusercontent.com/85680155/187632734-6b9a0d5c-0a3f-40e9-9ca4-ecfc92270b26.jpg">
<p>각 항목의 유효성 검사를 만족하면, 회원 가입이 완료됩니다.</p>

### 2) 로그인

<img width=30% src="=https://user-images.githubusercontent.com/85680155/187632950-f2929b94-ab22-408f-93d9-455c0d883d02.jpg">
<p>유저간의 신뢰성 확보를 위해 로그인 후 서비스 이용이 가능합니다.</p>

### 3) 비밀번호 재발급

<img width=30% src="https://user-images.githubusercontent.com/85680155/187633086-ae4e2f76-e3d0-4b3d-bb2b-6418801ace87.jpg">
<p>가입했던 아이디(이메일)를 입력하면, 해당 이메일로 비밀번호 초기화 링크가 전송됩니다.</p>

## 📌 메인피드

### 1) 무한스크롤링

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/5bd293135e9a7f775ee3ceb6da2955e8/%EB%AC%B4%ED%95%9C%EC%8A%A4%ED%81%AC%EB%A1%A4%EB%A7%81.jpg">
<p>
메인 피드에서는 본인이 팔로우한 유저가 쓴 게시글들이 무한 스크롤링으로 나옵니다.</p>

### 2) 유저검색

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/254c1022b468d708a01aa8cc6f7ab407/%EC%9C%A0%EC%A0%80%EA%B2%80%EC%83%89.jpg">
<p>우측 상단의 돋보기 버튼을 누르면, 유저 검색이 가능합니다.<br>
검색 결과를 클릭하면 해당 유저의 프로필로 이동합니다.</p>

## 📌 큐레이션 페이지

### 1) 이달의 핫스팟

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/ce0cc9cb6ff2c2b35b77d749613c8206/%EC%9D%B4%EB%8B%AC%EC%9D%98%ED%95%AB%EC%8A%A4%ED%8C%9F.jpg">
<p>매월 높은 등급을 가진 유저가 작성한 게시글 중 인기 게시글을 대분류별로 선정하여 보여주는 기능입니다.</p>

### 2) 태그 탐색기능

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/59b6ed994544f4922af301dd9f6be600/%EB%8C%80%EB%B6%84%EB%A5%98%EC%86%8C%EB%B6%84%EB%A5%98.jpg">
<p>원하는 태그를 누르고 검색하면 해당 게시글들이 나옵니다.</p>

### 3) 상세 게시글 조회

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/2c71f4b8a13550bbae6bf15c0829b5e8/%ED%81%90%EB%A0%88%EC%9D%B4%EC%85%98%EA%B2%8C%EC%8B%9C%EA%B8%80.jpg">
<p>자세히 보고 싶은 게시글이 있다면 클릭하여 상세 페이지로 이동할 수 있습니다.</p>

## 📌 게시글 상세 조회 페이지

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/c548c993228b6f13f083aa6d0c5135e1/%EA%B2%8C%EC%8B%9C%EA%B8%80%EB%94%94%ED%85%8C%EC%9D%BC.jpg">

### 1) 댓글 좋아요

</p>댓글을 달거나 좋아요를 클릭할 수 있습니다.</p>

### 2) 길찾기

<p>지도를 클릭하면 해당 위치를 목적지로 카카오맵 길찾기 서비스 페이지로 이동됩니다.</p>

### 3) 스크랩

<p>스크랩 버튼을 클릭하면, 내 프로필에서 확인할 수 있습니다.</p>

## 📌 그룹 페이지

### 1) 그룹 리스트 및 검색

<span>
<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/4ef34659583356731bf6cf6c7734826b/%EA%B7%B8%EB%A3%B9%EB%A6%AC%EC%8A%A4%ED%8A%B8.jpg">
<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/8b88a725ff8b5c57f244c9df234de848/%EB%AA%A8%EC%9E%84%EA%B2%80%EC%83%89.jpg">
</span>
<p>그룹 목록 확인 및 원하는 그룹을 검색할 수 있습니다.</p>

### 2) 가입 그룹 조회

<img width=30% src="https://user-images.githubusercontent.com/85680155/187633735-1254f97d-b2d4-4581-8662-dbb1f7a8d8d2.png">
<p>상단의 MY메뉴를 클릭하면 내가 가입한 그룹을 조회할 수 있습니다.</p>

### 3) 그룹 상세 조회 및 모임

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/b20e64d09e9791b311b19014564ffe45/%EA%B7%B8%EB%A3%B9%EA%B0%80%EC%9E%85.jpg">
<p>그룹을 클릭하면 상세 조회 페이지로 이동합니다.<br>
현재 진행중인 모임을 볼 수 있습니다.</p>

## 📌 게시글/그룹 등록

### 1) 등록

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/fc6674fa97d0ff8df5b1f9a8bceec408/%EA%B2%8C%EC%8B%9C%EA%B8%80%EA%B7%B8%EB%A3%B9%EC%83%9D%EC%84%B1.jpg">
<p>하단 메뉴의 등록 버튼을 누르면 게시글, 그룹 등록 페이지로 이동됩니다.</p>

## 📌 프로필 페이지

### 1) 게시글

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/99a4b1b4f1de2d569d6535fbf5fd5e9f/%ED%94%84%EB%A1%9C%ED%95%84%ED%8E%98%EC%9D%B4%EC%A7%80.jpg">

<p>내가 작성한 게시글을 볼 수 있습니다.</p>

### 2) 뱃지

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/ca9c891380ad62c183dd4f08ee7595b4/%ED%94%84%EB%A1%9C%ED%95%84%EB%B2%B3%EC%A7%80.jpg">

<p>내가 보유하고 있는 뱃지를 확인할 수 있습니다.</p>

### 3) 저장됨

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/12e95767b90387c421d11f2164263d8b/%ED%94%84%EB%A1%9C%ED%95%84%EC%8A%A4%ED%81%AC%EB%9E%A9.jpg">

<p>스크랩된 게시글을 볼 수 있습니다.</p>

### 4) 프로필 편집

<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/49795f672296243804ad2d76338eb89b/%ED%94%84%EB%A1%9C%ED%95%84%EC%88%98%EC%A0%95.jpg">

<p>내프로필 페이지에서는 프로필 편집을 할 수 있습니다.</p>

### 5) 취향분석

<span>
<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/911f583000fcefdcb8c7522589d4352e/%EC%B7%A8%ED%96%A5%EB%B6%84%EC%84%9D%EC%A7%80%EB%8F%84.jpg">
<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/7106ef2931a27f0bbc6ef352125783c1/%EC%B7%A8%ED%96%A5%EB%B6%84%EC%84%9D%EC%B0%A8%ED%8A%B8.jpg">
<img width=30% src="https://lab.ssafy.com/s07-webmobile2-sub2/S07P12A505/uploads/4e94409388ab6434336a642ccbf00ebf/%EC%B7%A8%ED%96%A5%EB%B6%84%EC%84%9D%ED%83%80%EC%9E%84%EB%9D%BC%EC%9D%B8.jpg">
</span>

<p>지도, 차트, 타임라인으로 유저가 작성한 게시글을 분석할 수 있습니다.<br>게시글에 등록한 사진의 메타데이터를 바탕으로 지도에 마커가 표시됩니다.</p>

### 6) 팔로우/팔로잉

<p>나를 팔로우하는 유저, 내가 팔로우하는 유저를 볼 수 있습니다.</p>
