package member.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import member.model.vo.Member;

public class MemberDao {

	private Properties memberQuery;
	
	public MemberDao() {
		memberQuery = new Properties();
		String fileName = MemberDao.class.getResource("/sql/member/member-query.xml").getPath();
		try {
			memberQuery.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		Member loginUser = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = memberQuery.getProperty("loginMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				loginUser = new Member(rset.getInt("user_no")
									 , rset.getString("user_id")
									 , rset.getString("user_pwd")
									 , rset.getString("user_name")
									 , rset.getString("phone")
									 , rset.getString("email")
									 , rset.getString("address")
									 , rset.getString("interest")
									 , rset.getDate("enroll_date")
									 , rset.getDate("modify_date")
									 , rset.getString("status"));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return loginUser;
	}


	public int insertMember(Connection conn, Member member) {
		int result =0;
		PreparedStatement pstmt = null;
		String sql = memberQuery.getProperty("insertMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,member.getUserId());
			pstmt.setString(2,member.getUserPwd());
			pstmt.setString(3,member.getUserName());
			pstmt.setString(4,member.getPhone());
			pstmt.setString(5,member.getEmail());
			pstmt.setString(6,member.getAddress());
			pstmt.setString(7,member.getInterest());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(pstmt);
		}
		
		return result;
	}


	public int updateMember(Connection conn, Member member) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = memberQuery.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getUserName());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getInterest());
			pstmt.setInt(6, member.getUserNo());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	public Member selectMember(Connection conn, int userNo) {
		Member updateMember = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = memberQuery.getProperty("selectMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				updateMember = new Member(rset.getInt("user_no")
									 , rset.getString("user_id")
									 , rset.getString("user_pwd")
									 , rset.getString("user_name")
									 , rset.getString("phone")
									 , rset.getString("email")
									 , rset.getString("address")
									 , rset.getString("interest")
									 , rset.getDate("enroll_date")
									 , rset.getDate("modify_date")
									 , rset.getString("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return updateMember;
	}


	public int updatePwd(Connection conn, int userNo, String userPwd, String newPwd) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = memberQuery.getProperty("updatePwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			
			pstmt.setString(1, newPwd);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
	
		
		return result;
		
	}


	public int deleteAccount(Connection conn, int userNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = memberQuery.getProperty("deleteMember");
		
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, userNo);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstmt);
			}
			
		return result;
	}
	
}
