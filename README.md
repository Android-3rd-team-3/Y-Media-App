## Y-Media App
유튜브 API를 이용해 관심 동영상을 리스트 형태로 저장 및 관리할 수 있게 하는 앱

## 기능 소개

### 홈 화면 & 디테일 화면

|즐겨찾기|영상 저장|카테고리별 추천 동영상|즐겨찾기 삭제|
|:-----:|:-----:|:-----:|:-----:|
|![룸 저장 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/3f57bb0d-651a-48df-8a40-e814201392e8)| ![앱 재시작시 룸 데이터 복구](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/8678f197-0539-4706-ada5-204c89980850)| ![카테고리 별 동영상 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/af2a516a-9ad4-4428-aea6-1bf8d0e21195)|![홈 디테일 왔다갔다 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/6c10f1ab-e9d4-4c68-9a60-69361596a784) |

### 서치 화면

| 동영상 검색| 키워드 검색 | 음성검색 |
|:-----:|:-----:|:-----:|
|![단순 검색 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/d9a16806-21bf-4b70-9d9a-2bee8ec046d1)|![검색 프리셋 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/953be99f-922a-4528-b66c-e006928bf02f) |![음성 검색 - Clipchamp로 제작](https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/4d725328-6c56-4b17-b989-0b38304c68a6)|

## 프로젝트 소개

### 디자인 패턴

- [ ] Clean Architecture
- [ ] Application Container Architecture
- [ ] MVVM pattern
- [ ] Repository pattern

### 기술 스택

- [ ] 비동기 처리: Coroutines
- [ ] 데이터 처리: Room
- [ ] API통신: Retrofit, Youtube Data API v3
- [ ] 이미지 로더: Glide

### 개발 기간
2024.5/13 ~ 5/23


## 트러블 슈팅

### [기능] 카테고리 별 채널 받아오는 문제
- 원인: 유튜브 API에서 채널 목록을 받아올 때 Category ID로 받아오는 기능이 deprecated 되어서 작동하지 않았다
- 해결 과정:

  채널 목록을 받아올 수 있는 방법인 id, forHandle, forUsername 중 여러 데이터를 한번에 받아올 수 있고 API에서 데이터를 받아올 수 있는 ID를 이용하기로 정했고, 
  id 파라미터는 콤마로 구분된 문자열을 입력받아 해당하는 채널의 리스트를 반환해주므로 카테고리 별 인기 동영상 리스트의 각 id 값을 콤마로 구분한 문자열으로 채널을 요청해서 리스트를 받아왔다

### [기능] 카테고리 별 동영상 받아오는 문제
- 원인: 유튜브 API에서 카테고리 별 동영상 목록을 받아올 때 특정 Category ID로 요청을 하면 400에러를 반환했다
- 해결 과정:

  API를 이용해 Category 목록을 받아올 때 snippet에 Boolean값의 assignable 속성이 있는데, 해당 값이 false인 category의 경우 요청을 보내면 400에러를 반환함을 찾아내 해결했다

### [구조] Domain 레이어를 나누며 발생한 문제
- 원인: Domain Layer를 추가하면서 Presentation Layer의 ViewModelFactory에서 Data Layer의 RepositoryImpl을 직접 주입받아 의존성 문제가 발생했다
- 해결 과정:

  Application Container Architecture를 도입하여 Presentation Layer에서 필요한 Data Layer의 정보를 Container에서 주입하는 것으로 변경하여 해결했다

### [구조] context 초기화 시점 문제
- 원인: Application이 초기화 되기 전(onCreate 전)에 RoomDatabase의 companion object 안의 코드가 실행되며 시점이 맞지 않는 문제가 발생했다
- 해결 과정:

  by lazy를 사용해 초기화 시점을 늦춰서 해결했다


  
## 프로젝트 와이어프레임

<img width="325" alt="image" src="https://github.com/Android-3rd-team-3/Y-Media-App/assets/157380033/5798e715-5f1f-4be7-94e8-342c4be64cc3">


 
