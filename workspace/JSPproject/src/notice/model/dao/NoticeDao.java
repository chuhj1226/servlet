package notice.model.dao;

import static common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import notice.model.vo.Notice;

public class NoticeDao {
   private Properties noticeQuery = new Properties();

   public NoticeDao() {
      String fileName = NoticeDao.class.getResource("/sql/notice/notice-query.xml").getPath();
      
      try {
         noticeQuery.loadFromXML(new FileInputStream(fileName));
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
//   전체 목록 조회
   public List<Notice> selectList(Connection conn) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      List<Notice> noticeList = new ArrayList<>();
      String sql = noticeQuery.getProperty("selectList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            noticeList.add(new Notice(rset.getInt("nno")
                              , rset.getString("ntitle")
                              , rset.getString("ncontent")
                              , rset.getString("nwriter")
                              , rset.getInt("ncount")
                              , rset.getDate("ndate")
                              , rset.getString("status")));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      return noticeList;
   }

//   검색 목록 조회
   public List<Notice> selectList(Connection conn, String searchCondition, String searchValue) {
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   List<Notice> noticeList = new ArrayList<>();
	   String sql = "";
	   
	   if(searchCondition.equals("title")) {
		   sql = noticeQuery.getProperty("selectTitleList");
	   } else {
		   sql = noticeQuery.getProperty("selectContentList");
	   }

	   try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, searchValue);
		
		rset = pstmt.executeQuery();
		
	    while(rset.next()) {
            noticeList.add(new Notice(rset.getInt("nno")
                              , rset.getString("ntitle")
                              , rset.getString("ncontent")
                              , rset.getString("nwriter")
                              , rset.getInt("ncount")
                              , rset.getDate("ndate")
                              , rset.getString("status")));
         }
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	   
	   return noticeList;
   }
   
   
public int insertNotice(Connection conn, Notice notice) {
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = noticeQuery.getProperty("insertNotice");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, notice.getNtitle());
		pstmt.setString(2, notice.getNcontent());
		pstmt.setString(3, notice.getNwriter());
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	
	
	
	return result;
}

public int increaseCount(Connection conn, int nno) {
	
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = noticeQuery.getProperty("increaseCount");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, nno);
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(pstmt);
	}
	
	return result;
	// 업데이트할거면 결과는 리절트
}

public Notice selectNotice(Connection conn, int nno) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	Notice notice = null;
	String sql = noticeQuery.getProperty("selectNotice");
	
	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, nno);
		
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			notice = new Notice(rset.getInt("nno")
			                    , rset.getString("ntitle")
			                    , rset.getString("ncontent")
			                    , rset.getString("nwriter")
			                    , rset.getInt("ncount")
			                    , rset.getDate("ndate")
			                    , rset.getString("status"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	
	return notice;
}

public int updateNotice(Connection conn, Notice notice) {
	
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = noticeQuery.getProperty("updateNotice");

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, notice.getNtitle());
		pstmt.setString(2, notice.getNcontent());
		pstmt.setInt(3, notice.getNno());
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	
	return result;
	
}

public int deleteNotice(Connection conn, int nno) {
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = noticeQuery.getProperty("deleteNotice");

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, nno);
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	return result;
}


}