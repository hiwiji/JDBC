-- 로그인 SQL

SELECT MEMBER_NO, MEMBER_ID, MEMBER_NM, MEMBER_GENDER,
TO_CHAR(ENROLL_DATE, 'YYYY"년" MM"월", DD"일" HH24:MI:SS') ENROLL_DATE
FROM MEMBER 
WHERE MEMBER_ID = 'user01' 
AND MEMBER_PW = 'pass01'
AND SECESSION_FL = 'N';



-- 아이디 중복검사 (조회니까 select)
SELECT COUNT(*) FROM MEMBER
WHERE MEMBER_ID  = ?
AND SECESSION_FL = 'N';


-- 회원가입
INSERT INTO MEMBER
VALUES(SEQ_MEMBER_NO.NEXTVAL, ?, ?, ?, ? DEFAULT, DEFAULT)