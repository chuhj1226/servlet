package member.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

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

	public int insertMember(Member member) {
		Connection conn = getConnection();
		
		int result = memberDao.insertMember(conn, member);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}

	public Member updateMember(Member member) {
		Connection conn = getConnection();
		Member updatedMember = null;
		
		// 1. 수정하려는 정보가 담긴 member 객체를 가지고 updateMember
		int result = memberDao.updateMember(conn, member);
		
		// 2. 수정이 잘 되었다면 수정 된 정보의 member 객체 select
		if(result > 0 ) {
			updatedMember = memberDao.selectMember(conn,member.getUserNo());
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updatedMember;
	}

	public Member updatePwd(int userNo, String userPwd, String newPwd) {
		Connection conn = getConnection();
		Member updatedMember =null;
		
		int result = memberDao.updatePwd(conn, userNo, userPwd, newPwd);
		
		if(result > 0 ) {
			updatedMember = memberDao.selectMember(conn, userNo);
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		
		return updatedMember;
	}

	public int deleteAccount(int userNo) {
		Connection conn = getConnection();
		
		int result = memberDao.deleteAccount(conn, userNo);
		
		if(result>0) {
			
			commit(conn);
		}else {
			rollback(conn);
		
		}
		
		close(conn);
		
		
		return result;
		
		
	}


}
