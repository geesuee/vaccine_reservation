insert into Hospital values('삼성병원', '강남구', 2, 3, 3);
insert into Hospital values('성심병원', '강동구', 1, 4, 4);
insert into Hospital values('아산병원', '송파구', 0, 4, 5);

insert into Vaccine values('화이자', 12, 21, 'mRNA', '-90도 ~ -60도', 6);
insert into Vaccine values('모더나', 18, 28, 'mRNA', '-25도 ~ -15도', 7);
insert into Vaccine values('AZ', 30, 56, '바이러스 벡터', '2도 ~ 8도', 6);

insert into Users values(960916, '곽형림', 15, '강남구', '2021-08-14', '2021-09-03', '화이자', '삼성병원');
insert into Users values(940804, '유영훈', 19, '강동구', '2021-08-23', '2021-09-19', '모더나', '성심병원');
insert into Users values(971202, '배지수', 31, '송파구', '2021-07-23', '2021-09-16', 'AZ', '아산병원');
insert into Users values(990523, '김혜경', 20, '강남구', '2021-08-14', '2021-09-03', '화이자', '삼성병원');

COMMIT;
