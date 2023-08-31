---
# zerobase__Mission
---
## 자바와 웹 그리고 데이터베이스를 통해서 프로젝트를 진행

- 프로젝트 명 : 내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발
- 프로젝트 목적 : 공공 데이터 자원을 끌어와서 내가 서비스 하고자 하는 데이터로
마이그레이션하여 웹을 기반으로 하여 원하는 형태로 출력하고자 함. 또한, 사용한 히스토리에
대해서 데이터베이스에 저장하는 기능과 이를 출력하는 기능을 구현하고자 함.

---
## ✅ 기술 스택
- 언어 : java (maven project)
- SQL DB : SQLite
- server : Tomcat 8.5
- tool : eclipse

---
## ✅ 주요 내용

- gson 사용
- geolocation 사용하여 내 위치 찾기 완료
- 내 주변 와이파이 20개 가져오기 완료
- API 이용해서 23304개 웹에 확인 완료

---
## ✅ 에러 내용

1. table database is locked : sql이 바쁘다면서 갑자기 db가 잠겨버림, 하지만 테이블에는 정보가 다 들어가 있음

2. A PRIMARY KEY constraint failed unique constraint fail : primary key가 겹치는 키가 없는데 이 에러 등장


<BR>
제출 기한 내 결국 jsp만 구현하고 db가 잠겨 해당 기능이 필요한 항목은 아무것도 실행되지 않았습니다. 

---
- DB 잠긴 이유 (추측) <BR>
EXCLUSIVE: 프로세스가 파일을 쓰는 상태, Exclusive Lock에 존재는 DB 파일에 데이터를 입력하기 위함입니다.<BR>
데이터의 입력은 Insert, Update, Delete가 해당하는데, 해당 상태 또한 누구도 DB 파일에 접근하지 못하며, 접근할 경우, Database is locked라는 상태메시지가 출력됩니다.<BR>
-> 어플 DB Browser for SQLite를 사용할 때 켜져 있는 어플을 확인하지 못한 채 다시 열고 진행해서 DB가 잠긴 게 아닐까 추측하고 있습니다.
