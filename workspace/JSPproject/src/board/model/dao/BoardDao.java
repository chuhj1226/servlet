package board.model.dao;

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

import board.model.vo.Attachment;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Search;

public class BoardDao {
   
   private Properties boardQuery = new Properties();
   
   public BoardDao() {
      // properties : 키와 값을 String타입으로 제한한 Map컬렉션
      String fileName = BoardDao.class.getResource("/sql/board/board-query.xml").getPath();
      // path 경로를 담음
      try {
         boardQuery.loadFromXML(new FileInputStream(fileName));
         // xml파일을 properties 파일로 변환하여 읽음
      } catch (IOException e) {
         e.printStackTrace();
      }
   }   

   
   // 총 게시글의 갯수 조회
   public int getListCount(Connection conn, Search search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      int listCount = 0;
      String sql = boardQuery.getProperty("getListCount");   // default
      
      // 검색된 목록을 조회해야 하는 경우 다른 SQL문 수행
      if(search.getSearchCondition() != null && search.getSearchValue() != null) {
         if(search.getSearchCondition().equals("title")) {
            sql = boardQuery.getProperty("getTitleListCount");
         } else if(search.getSearchCondition().equals("content")) {
            sql = boardQuery.getProperty("getContentListCount");
         } else if(search.getSearchCondition().equals("writer")) {
            sql = boardQuery.getProperty("getWriterListCount");
         }
      }
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         // 검색 SQL문을 실행하는 경우 검색 값 설정
         if(search.getSearchCondition() != null && search.getSearchValue() != null) {
            pstmt.setString(1, search.getSearchValue());
         }
         
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            listCount = rset.getInt(1);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return listCount;
   }


   public List<Board> selectList(Connection conn, PageInfo pi, Search search) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = boardQuery.getProperty("selectList");
      List<Board> boardList = new ArrayList<>();
      
      // 검색 시 수행할 쿼리문 변경
      if(search.getSearchCondition() != null && search.getSearchValue() != null) {
    	  if(search.getSearchCondition().equals("title")) {
    		  sql = boardQuery.getProperty("selectTitleList");
    	  }else if(search.getSearchCondition().equals("content")) {
    		  sql = boardQuery.getProperty("selectContentList");
    	  }else if(search.getSearchCondition().equals("writer")) {
    		  sql = boardQuery.getProperty("selectWriterList");
    	  }
    	  
      }
      
      try {
         pstmt = conn.prepareStatement(sql);
         
         int startRow = (pi.getPage() - 1) * pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit() - 1;
         
         int index = 1;
         // 검색 sql 실행 시 
         if(search.getSearchCondition() != null && search.getSearchValue() != null) {
        	 pstmt.setString(index++, search.getSearchValue());
         }
         
         pstmt.setInt(index++, startRow);
         pstmt.setInt(index, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            Board board = new Board();
            board.setBid(rset.getInt("bid"));
            board.setCname(rset.getString("cname"));
            board.setBtitle(rset.getString("btitle"));
            board.setUserName(rset.getString("user_name"));
            board.setBcount(rset.getInt("bcount"));
            board.setCreateDate(rset.getDate("create_date"));
            boardList.add(board);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return boardList;
   }


   public int increaseCount(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("increaseCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }


   public Board selectBoard(Connection conn, int bid) {
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      Board board = null;
      String sql = boardQuery.getProperty("selectBoard");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, bid);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            board = new Board();
            board.setBid(rset.getInt("bid"));
            board.setCid(rset.getInt("cid"));
            board.setCname(rset.getString("cname"));
            board.setBtitle(rset.getString("btitle"));
            board.setBcontent(rset.getString("bcontent"));
            board.setBwriter(rset.getInt("bwriter"));
            board.setUserName(rset.getString("user_name"));
            board.setBcount(rset.getInt("bcount"));
            board.setCreateDate(rset.getTimestamp("create_date"));
            board.setModifyDate(rset.getTimestamp("modify_date"));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return board;
   }


   public int insertBoard(Connection conn, Board board) {
      PreparedStatement pstmt = null;
      int result = 0;
      String sql = boardQuery.getProperty("insertBoard");
      
      try {
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, board.getBtype());
         pstmt.setInt(2, board.getCid());
         pstmt.setString(3, board.getBtitle());
         pstmt.setString(4, board.getBcontent());
         pstmt.setInt(5, board.getBwriter());
         
         result = pstmt.executeUpdate();
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(pstmt);
      }
      
      return result;
   }


public int updateBoard(Connection conn, Board board) {
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = boardQuery.getProperty("updateBoard");

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, board.getCid());
		pstmt.setString(2, board.getBtitle());
		pstmt.setString(3, board.getBcontent());
		pstmt.setInt(4, board.getBid());
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	
	return result;
}


public int deleteBoard(Connection conn, int bid) {
	PreparedStatement pstmt = null;
	int result = 0;
	String sql = boardQuery.getProperty("deleteBoard");

	try {
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, bid);
		
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		close(pstmt);
	}
	
	return result;
}


public List<Board> selectGallaryList(Connection conn) {
	PreparedStatement pstmt = null;
	ResultSet rset = null;
	List<Board> boardList = new ArrayList<>();
	String sql = boardQuery.getProperty("selectGalleryList");
	
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setBid(rset.getInt("bid"));
				board.setCname(rset.getString("cname"));
				board.setBtitle(rset.getString("btitle"));
				board.setUserName(rset.getString("user_name"));
				board.setBcount(rset.getInt("bcount"));
				
				List<Attachment> photoList = new ArrayList<>();
				Attachment photo = new Attachment();
				photo.setFilePath(rset.getString("file_path"));
				photo.setChangeName(rset.getString("change_name"));
				photoList.add(photo);
				board.setPhotoList(photoList);
				
				boardList.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
	
	return boardList;
}
   

}