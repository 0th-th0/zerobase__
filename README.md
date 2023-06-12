---
# zerobase__Mission1
---
@ 자바와 웹 그리고 데이터베이스를 통해서 프로젝트를 진행

-프로젝트 명 : 내 위치 기반 공공 와이파이 정보를 제공하는 웹서비스 개발
-프로젝트 목적 : 공공 데이터 자원을 끌어와서 내가 서비스 하고자 하는 데이터로
마이그레이션하여 웹을 기반으로 하여 원하는 형태로 출력하고자 함. 또한, 사용한 히스토리에
대해서 데이터베이스에 저장하는 기능과 이를 출력하는 기능을 구현하고자 함.

---
기술 스택
---
-언어 : java (maven project)
-SQL DB : SQLite
-server : Tomcat 8.5
-tool : eclipse

---
주요 내용
---

-gson 사용
-geolocation 사용하여 내 위치 찾기 완료
-내 주변 와이파이 20개 가져오기 완료
-API 이용해서 23304개 웹에 확인 완료

---
에러 내용
---
-table database is locked
sql이 바쁘다면서 갑자기 db가 잠겨버림
하지만 테이블에는 정보가 다 들어가있음
-A PRIMARY KEY constraint failed unique constraint fail
primary key가 겹치는 키가 없는데 이 에러 등장

결국 jsp만 구동하고 db가 잠겨 아무것도 구동이 되지 않았습니다...

