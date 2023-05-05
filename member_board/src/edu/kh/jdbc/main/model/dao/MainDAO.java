package edu.kh.jdbc.main.model.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import edu.kh.jdbc.member.vo.Member;
import static edu.kh.jdbc.common.JDBCTemplate.*;

public class MainDAO {

	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private Properties prop = null;
	// Map<String, String> 제한, XML 파일 읽고/쓰는데 특화
	
	
	//기본생성자
	public MainDAO() {
		
		// dao가 기본생성자일 때 기본적으로 해야되는 것들 만드는거 끝 
		// 1. try-catch문 작성해서 properties 객체 생성하기 
		
		try {
			prop = new Properties(); //Properties 객체 생성
			
			prop.loadFromXML(new FileInputStream("main-query.xml"));
			//main-query.xml 파일의 내용을 읽어와 Properties 객체에 저장
			
		} catch(Exception e ) {
			e.printStackTrace();
		}
		
	}
	
	

	
	/** 로그인 DAO
	 * @param conn
	 * @param memberId
	 * @param memberPw
	 * @return loginMember
	 * @throws Exception
	 */
	public Member login(Connection conn, String memberId, String memberPw) throws Exception{
		
		// dao에서 해야 될 것들
		
		// 1. 결과 저장용 변수 선언
		Member loginMember = null;
		
		try {
			// 2. SQL 얻어오기 (main-query.xml 파일에 작성된 SQL)
			String sql = prop.getProperty("login");

			// 3. PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 4. ?에 알맞은 값 대입   
				// 여기서 계속 오류뜨는거 신경쓰여서 throws 던짐
				// -> Main service로 오류뜸
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			
			// 5. SQL(SELECT) 수행 결과 (ResultSet) 반환 받기  
			//수행해서 되돌아오는 형태? ResultSet 상태(조회)
			rs = pstmt.executeQuery();
			
			// 6. 조회 결과가 있을 경우
			// 		컬럼값을 모두 얻어와
			//		Member 객체를 생성하여 loginMember 대입
			//    (결과 저장용 변수인 loginMember에 넣는다는 뜻)
			
			if(rs.next()) {
				
				//String memberId = rs.getInt("MEMBER_NO");
				
				loginMember = new Member(rs.getInt("MEMBER_NO"),
							memberId,
							rs.getString("MEMBER_NM"),
							rs.getString("MEMBER_GENDER"),
							rs.getString("ENROLL_DATE") );
			}
			
					
		} finally {
			// 7. 사용한 JDBC 객체 자원 반환
			close(rs);  // 여기 밑줄쳐지는건 JDBCTemplate.* 해주면 해제됨
			close(pstmt);
		}

		// 8. 조회결과 반환
		return loginMember;
	}


	
	
	/** 아이디 중복검사 DAO
	 * @param conn
	 * @param memberId
	 * @return result
	 * @throws Exception
	 */
	public int inDupCheck(Connection conn, String memberId) throws Exception {
		int result = 0;
		
		try {
			String sql = prop.getProperty("idDupCheck");
			
			pstmt = conn.prepareStatement(sql); 
			// throws Exception쓰면 줄 없어짐
			
			pstmt.setString(1, memberId);
			
			rs = pstmt.executeQuery();
			
			// COUNT(*)
			// 1
						
			if(rs.next()) {
				//result = rs.getInt("Count(*)");
				result = rs.getInt(1); // 컬럼순서
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return result;
	}

	

	/** 회원가입 DAO
	 * @param conn
	 * @param member
	 * @return result
	 * @throws Exception
	 */
	public int signUp(Connection conn, Member member)throws Exception {
		int result = 0;
			
		try {
			
			String sql = prop.getProperty("signUp");
			
			pstmt = conn.prepareStatement(sql);
			// service로 던져주기
			
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getMemberPw());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getMemberGender());
			
			result = pstmt.executeUpdate();
			// 가로안에 sql구문 넣으면 안넣은거랑 똑같은 결과값이 나옴
		
		} finally {
			close(pstmt);
			
		}
		
		return result;
	}

}
