package member.model.service;

import java.sql.Connection;
import static common.JDBCTemplate.*;

import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();

	public Member loginMember(String userId, String userPwd) {
		// JDBCTemplate의 getConnection 작성
		Connection conn = getConnection();
		// System.out.println(conn);
		
		
		// MemberDao 클래스 만들고 loginMember 메소드 작성
		Member loginMember = memberDao.loginMember(conn, userId, userPwd);
		
		// 연걸 객체 반환
		close(conn);
		
		return loginMember;
		
		
	}

}
