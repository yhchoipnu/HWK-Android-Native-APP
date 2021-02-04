# HWK-Android-Native-APP

## 파일 설명
  1. MainActivity.java
 	- MainActivity

  2. LocationActivity.java
 	- 위치정보 관련 로직 (갱신 주기 : 5s로 임의 설정하였음)

  3. EstimateActivity.java
 	- 견적서 작성 및 csv 파일로 저장 로직
 	- csv 파일 저장 위치: 파일관리자>내부저장소>Android>data>프로젝트명(현재: S3LabBahk)>files에 저장되도록 하였음

  4. EstimateDateFragment.java
 	- 견적서 액티비티의 달력용 로직

  5. ReportActivity.java
 	- 리포트 작성 및 csv 파일로 저장 로직

  6. ReportDateFragment.java
 	- 리포트 액티비티의 달력용 로직

  7. EmailActivity.java
 	- 이메일 작성, 발송 로직

  8. res/layout/conetent_main.xml
 	- MainActivity와 대응되는 디폴트 xml 파일

  9. res/layout/activitiy_loc.xml
 	- LocationActivity와 대응되는 xml 파일

 10. res/layout/activity_estimate.xml
 	- EstimateActivity와 대응되는 xml 파일

 11. res/layout/activity_report.xml
 	- ReportActivity와 대응되는 xml 파일

 12. res/layout/activitiy_email.xml
 	- EmailActivity와 대응되는 xml 파일

 13. res/drawable/stroke.xml         // TextView, ImageView 등의 테두리 설정
 	- Keras를 이용한 딥러닝 학습 파일

 14. res/values/arrays.xml             // Spinner 등에 출력해 줄 array list 값 설정
 	- Keras를 이용한 딥러닝 학습 파일

 15. res/AndroidManifest.xml         // 1. Activities 명시적 추가 2. GPS, REST API, 스토리지(파일 등) 권한 설정 등
 	- Keras를 이용한 딥러닝 학습 파일

 16. gradle/build.gradle(:app)         // 외부 라이브러리의 dependencies 설정 등
 	- Keras를 이용한 딥러닝 학습 파일

 17. gradle/build.gradle(프로젝트명) // 현재 기준 : S3LabBahk (이는, APP name으로도 사용됨)
 	- Keras를 이용한 딥러닝 학습 파일

## 라이브러리 목록

### 추가한 외부 라이브러리
- [opencsv.jar](https://imagemagick.org/)
 	- csv 포맷의 파일로 저장
- [commons-io.jar](https://github.com/jankovicsandras/imagetracerjava)
 	- File Attachment 관련 I/O

## 참고한 웹사이트

### Google Developers
- [Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk/usage-and-billing?hl=ko)
 	- Google MAP API 결제 및 연동, 사용 방법 등의 정보를 제공하는 공식 가이드 사이트
 	- Map Load는 무료이나, Steet View는 유료이니, 추가 개발 시 주의 요망
 	- Google Map API functions 등 정보 제공

### Google Cloud Platform
- [Google Cloud Platform](https://console.cloud.google.com/billing)
 	- Google Map API를 사용하기 위해, 사용자 인증이 필요함 (=> key 정보를 제공하는 사이트)
 	- Google Cloud Platform > Google Maps Platform > 사용자 인증 정보

### 선박 입‧출항 정보 REST API
- [공공 데이터](https://data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15000990)
 	- 선박 입‧출항 정보를 받아오는 REST API 사용법 관련 정보 제공 사이트
 	- [부산항만공사 가이드북](https://www.chainportal.co.kr/template/OpenAPIGuide.pdf)