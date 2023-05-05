package edu.kh.jdbc.member.view;

import edu.kh.jdbc.member.vo.Member;

public class MemberView{

	// 로그인 회원 정보 저장용 변수 
	private Member loginMember = null;  // 멤버안에서만 쓸거 필요함


	public void memberMenu(Member loginMember) {
		
		// 전달 받은 로그인 회원 정보를 필드에 저장
		this.loginMember = loginMember;

	}
	
}