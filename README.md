# 백신 접종 예약 서비스
백신 접종 예약 서비스는 사용자를 `**관리자**` , `**접종 예약자**` 로 분류하여

- 관리자는 **병원, 백신, 접종 예약 현황 데이터를 조회/추가/수정/삭제**하고
- 접종 예약자는 **본인 연령 대상 백신 확인**, **주소 인근 병원 잔여 백신 조회** 등 기능과 함께

  백신 **접종 예약 조회/추가/수정/삭제** 할 수 있습니다.

👉 [Notion Link](https://fate-riverbed-f96.notion.site/541f8815144240c782c545605654153f)
</br>
</br>

## ⚙ 개발 환경
- `Java 1.8`
- `Eclipse`
- `OracleDB`
- `JPA` / `Lombok`
- `JUnit5`
</br>
</br>

## 📅 프로젝트 진행 타임라인
![123](https://user-images.githubusercontent.com/87046726/130897224-45b8915a-69d1-4282-8272-83fb65231910.png)
</br>
</br>

# 1. Database Modeling
![vaccine](https://user-images.githubusercontent.com/87046726/130896827-09de983e-03d5-44dd-9ed0-82287bc1be20.png)

![테이블 스키마](https://user-images.githubusercontent.com/87046726/130896891-780f45d6-1d3d-436a-935a-bbe9aeea8872.png)
</br>
</br>

# 2. SQL
- DDL

    ```sql
    drop table Hospital cascade constraints;
    drop table Users cascade constraints;
    drop table Vaccine cascade constraints;

    create table Hospital (
    	name 		varchar2(255) primary key,  
    	location 	varchar2(255) not null, 
    	pfizer 		number(10,0) not null,
    	moderna 	number(10,0) not null, 
    	az 			number(10,0) not null
    );

    create table Users (
    	id_num 			number(19,0) primary key, 
    	name 			varchar2(255) not null, 
    	age 			number(10,0) not null, 
    	address 		varchar2(255) not null, 
    	date1 			varchar2(255) not null, 
    	date2 			varchar2(255) not null,
    	vaccine_name 	varchar2(255) not null,
    	hospital_name 	varchar2(255) not null
    );

    create table Vaccine (
    	name 		varchar2(255) primary key, 
    	target_age 	number(10,0) not null, 
    	period 		number(10,0) not null, 
    	platform 	varchar2(255) not null, 
    	temperature varchar2(255) not null,
    	storage 	number(10,0) not null
    );

    alter table Users add constraint Hospital foreign key (hospital_name) references Hospital;
    alter table Users add constraint Vaccine foreign key (vaccine_name) references Vaccine;
    ```
- DML

    ```sql
    insert into Hospital values('삼성병원', '강남구', 2, 3, 3);
    insert into Hospital values('성심병원', '강동구', 1, 4, 4);
    insert into Hospital values('아산병원', '송파구', 0, 4, 5);

    insert into Vaccine values('화이자', 12, 21, 'mRNA', '-90도 ~ -60도', 6);
    insert into Vaccine values('모더나', 18, 28, 'mRNA', '-25도 ~ -15도', 7);
    insert into Vaccine values('AZ', 30, 56, '바이러스 벡터', '2도 ~ 8도', 6);

    insert into Users values(960916, '곽형림', 15, '강남구', '20210814', '20210903', '화이자', '삼성병원');
    insert into Users values(940804, '유영훈', 19, '강동구', '20210823', '20210919', '모더나', '성심병원');
    insert into Users values(971202, '배지수', 31, '송파구', '20210723', '20210916', 'AZ', '아산병원');
    insert into Users values(990523, '김혜경', 20, '강남구', '20210814', '20210903', '화이자', '삼성병원');

    COMMIT;
    ```
</br>
</br>

# 4. Function
- Vaccine-Project flow

    ![Untitled Diagram (2)](https://user-images.githubusercontent.com/87046726/130896982-b9b43d61-c1b5-48fb-9d54-1d24fb63d598.png)
    
**HospitalController : 병원 관련 기능**

- `getAllHospital()` : 병원 정보 전체 조회
- `getHospital()` : {병원 이름}으로 단일 병원 정보 조회
- `getReservationHospital()` : {병원 위치}로 단일 병원 정보 조회
- `addHospital()` : 새로운 병원 저장
- `updateHospitalLocation()` : {병원 이름}으로 병원 위치 수정
- `updateHospitalAllVaccine()` : {병원 이름}으로 해당 병원 보유 전체 백신 수량 수정
- `updateHospitalVaccine()` : {병원 이름, 백신 이름}으로 해당 병원 보유 특정 백신 수량 수정
- `deleteHospital()` : {병원 이름}으로 병원 정보 삭제

**VaccineController : 백신 관련 기능**
- `getAllVaccine()` : 백신 정보 전체 조회
- `getVaccine()` : {백신 이름}으로 단일 백신 정보 조회
- `updateVaccine()` : {백신 이름}으로 해당 백신 플랫폼, 보관 온도 수정
- `updateVaccineTargetAge()` : {백신 이름}으로 해당 백신 접종 연령 수정
- ~~`addVaccine()` : 새로운 백신 저장~~
- ~~`deleteVaccine()` : {백신 이름}으로 백신 정보 삭제~~

**UsersController : 접종 예약자 관련 기능**
- `getAllUsers()` : 접종 예약자 정보 전체 조회
- `getAllUsersByHospital()` : {병원 이름}으로 해당 병원 접종 예약자 정보 다중 조회
- `getVaccineDate()` : {주민등록 번호}로 접종 예약 일자 조회
- `addUser()` : 새로운 접종 예약자 저장(=접종 예약)
- `updateUserDate()` : {주민등록 번호}로 해당 접종 예약자의 1차/2차(선택) 접종 날짜 수정
- `updateUserAddress()` : {주민등록 번호}로 해당 접종 예약자 주소 수정
- `deleteUser()` : {주민등록 번호}로 접종 예약자 삭제(=접종 예약 취소)
</br>
</br>

## 🚩 아쉬운 점 / 미흡한 점
- **정규화가 미흡한 DB 설계**

    DB 설계 시 검색의 용이성을 고려,  병원 테이블 내 **병원 정보와 백신 개수 정보 혼재**, 접종 예약자 테이블 내 **예약자 개인정보와 예약 정보 혼재 (정규화 미흡)**

    → 백신을 새로 추가하거나 삭제할 시 병원 테이블을 수정할 수 없어 백신 수정 사항 반영 불가

    → 백신/병원이 삭제될 시 예약자 정보 내 백신/병원 정보까지 삭제되거나(null) 예약자 정보도 같이 삭제되거나 위의 정보는 삭제 불가

    ✅ *여러 테이블을 연결해서 정보를 검색하는 것은 다소 어려워지겠지만..*

    병원 테이블에는 병원 정보만, 

    잔여 백신 테이블에는 잔여 백신 종류, 개수, 보유 병원 등 잔여 백신 정보만,

    백신 테이블에는 백신 정보만,

    예약자 테이블에는 예약 정보만,

    예약 내역 테이블에는 예약자 이름, 예약 백신 이름, 예약 병원 이름 등 예약 내역 정보만

    *따로 분리하여 테이블을 만들어야 위와 같은 문제가 발생하지 않는다.*

- **서비스 사용자와 구현 기능의 지속적인 수정으로 코드 작성 비효율 발생**

    최종 구현 기능을 정해두고, 해당 기능에 필요한 메소드만을 요구사항에 맞춰 작성했으면 좋았을텐데, 그러지 않아서 최종적으로 Controller를 합치고, StartView와 연결하는 과정에서

    - 메소드를 새로 만들거나
    - 기존의 메소드를 사용하지 않거나
    - 동일한 로직을 갖지만 반환값만 다른 메소드를 만들거나 하는 과정을 거쳤고,

    ![그림1](https://user-images.githubusercontent.com/87046726/130897044-c6689527-d27a-4048-b960-e7c69746b835.png)

    입출력 값을 받아 사용자 화면을 구성하는 부분에서 **Controller 안 메소드와 DAO 안 메소드를 혼재하여 사용하게 되는 문제**가 발생함...😥

- **깃허브를 활용한 협업의 어려움**

    → 각자 branch에 자신의 코드를 push한 뒤 pull request를 통해 한 개 branch로 합치는 과정에서

    1. branch를 삭제하고 재생성하면서 각 branch의 history가 달라져 merge 불가
    2. 여러 사람이 동일한 파일을 수정하는 경우가 있어 merge시 confict 다수 발생
    3. 수정 전 코드를 pull하여 작업 후 (수정 전 코드라는 사실을 모르고) 제일 먼저 merge해서 수정 사항 증발..
</br>
</br>

## 👥 팀 구성원
- 곽형림 ([https://github.com/gudfla1815](https://github.com/gudfla1815))
- 유영훈 ([https://github.com/yyhhha](https://github.com/yyhhha))
- 배지수 ([https://github.com/geesuee](https://github.com/geesuee))
