package edu.kh.jdbc.main.model.service;

import java.sql.Connection;

import edu.kh.jdbc.main.model.dao.MainDAO;
import edu.kh.jdbc.member.vo.Member;

import static edu.kh.jdbc.common.JDBCTemplate.*; 
// import 뒤 static 붙이고 맨뒤에 *별찍고 ;세미콜론  ??
// import static 패키지명.클래스명.*;


//Service : 데이터 가공, 트랜잭션 제어 처리 
public class MainService {
	
	private MainDAO dao = new MainDAO();

	/** 로그인 서비스
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */ 
	// 파라미터 (param)는 나열하면 안되고 따로따로 적어줘야함
	public Member login(String memberId, String memberPw) throws Exception {
		// 로그인메서드에서 id, pw 받음
		
		
		//서비스에서 해줘야 될 것들
		
		//1. 커넥션 생성
		Connection conn = getConnection();
		
		//2. DAO 메서드 호출 후 결과 반환 받기
		Member loginMember = dao.login(conn, memberId, memberPw);
		//커넥션이랑, 서비스에서 반환받은것도 꼭 넣어주기!! 
		// 다 입력 후 login에 createMethod 클릭 (dao에 입력됨)
		
		
		//3. 커넥션 반환
		close(conn); // jdbc템플릿에 있는거
		
		
		//4. 조회결과 반환
		return loginMember;
		
	}

	/** 아이디 중복 검사 서비스
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int idDupCheck(String memberId) throws Exception {
		
		//1. 커넥션 생성
		Connection conn = getConnection();
		
		//2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.inDupCheck(conn, memberId);
		
		//3. 커넥션 반환
		close(conn); // jdbc템플릿에 있는거
		
		//4. 조회결과 반환
		return result;
	}

	/** 회원 가입 서비스
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Member member) throws Exception {
			
		//1. 커넥션 생성
		Connection conn = getConnection();
		
		//2. DAO 메서드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, member); // exception 던져주기
		
		//3. 트랜잭션 제어 처리
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		//4. 커넥션 반환
		close(conn); 
		
		return result;
	}

}
