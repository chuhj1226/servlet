package board.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.model.dao.BoardDao;
import board.model.vo.Board;
import board.model.vo.PageInfo;
import board.model.vo.Search;
import notice.model.vo.Notice;
public class BoardService {
   
   private BoardDao boardDao = new BoardDao();
   
   public Map<String, Object> selectList(int page, Search search) {
      Connection conn = getConnection();
      
      // 1. 게시글 총 개수 구하기
      int listCount = boardDao.getListCount(conn, search);
      System.out.println(listCount);
      // 2. PageInfo 객체 만들기
      PageInfo pi = new PageInfo(page, listCount, 10, 10);
      
      // 3. 페이징 처리 된 게시글 목록 조회
      List<Board> boardList = boardDao.selectList(conn, pi, search);
      
//      System.out.println(pi);
//      System.out.println(boardList);
      
      Map<String, Object> returnMap = new HashMap<>();
      
      returnMap.put("pi", pi);
      returnMap.put("boardList", boardList);
      close(conn);
      
      
      return returnMap;
   }

public int increaseCount(int bid) {
	Connection conn = getConnection();
	
	int result = boardDao.increaseCount(conn, bid);
	
	if(result>0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public Board selectBoard(int bid) {
	Connection conn = getConnection();
	Board board = boardDao.selectBoard(conn,bid);
	close(conn);
	return board;
}

public int insertBoard(Board board) {
	Connection conn = getConnection();
	
	int result = boardDao.insertBoard(conn, board);
	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public int updateBoard(Board board) {

	Connection conn = getConnection();
	
	int result = boardDao.updateBoard(conn, board);
	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public int deleteBoard(int bid) {
	Connection conn = getConnection();

	
	int result = boardDao.deleteBoard(conn, bid);
	
	if(result> 0) {
		commit(conn);
	}else {
		rollback(conn);
	}
	
	close(conn);
		
	return result;
}

public List<Board> selectGalleryList() {
	Connection conn = getConnection();

	List<Board> boardList = boardDao.selectGallaryList(conn);
	
	close(conn);
	
	return boardList;
	
	
	
}

}