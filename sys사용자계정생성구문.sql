-- 오라클 11G 이전 버전의 SQL 작성을 가능하게 하는 구문
ALTER SESSION SET "_ORACLE_SCRIPT" = TRUE;

-- 사용자 계정 생성
CREATE USER workbook IDENTIFIED BY 1234;

-- C## : 일반 사용자(사용자 계정을 의미)
-- CREATE USER C##kh IDENTIFIED BY kh1234;

-- 사용자 계정 권한 부여 설정
GRANT RESOURCE, CONNECT TO workbook;

--객체가 생성될 수 있는 공간 할당량 지정 
ALTER USER workbook DEFAULT TABLESPACE SYSTEM QUOTA UNLIMITED ON SYSTEM;
