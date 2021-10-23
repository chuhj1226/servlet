package notice.model.service;

import java.sql.Connection;
import java.util.List;
import static common.JDBCTemplate.*;

import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;

public class NoticeService {
   
   private NoticeDao noticeDao = new NoticeDao();
   
   public List<Notice> selectList(String searchCondition, String searchValue) {
      Connection conn = getConnection();
      List<Notice> noticeList;
      // 검색 조건과 검색 키워드가 모두 전달되었다면 검색된 목록 조회
      if(searchCondition != null && searchValue != null) {
    	  noticeList = noticeDao.selectList(conn, searchCondition, searchValue);
      } else {	// 전달되지 않았다면 모든 목록 조회
    	  noticeList = noticeDao.selectList(conn);
      }
      close(conn);
      
      return noticeList;
   }

public int insertNotice(Notice notice) {
	Connection conn = getConnection();
	
	int result = noticeDao.insertNotice(conn, notice);
	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public Notice selectNotice(int nno) {
	Connection conn = getConnection();
	Notice notice = null;
	
	
	// 1. 조회수 증가
	int result = noticeDao.increaseCount(conn, nno);
	
	// 2. 해당 게시글 조회
	if(result> 0) {
		// 2. 해당 게시글 조회
		notice = noticeDao.selectNotice(conn,nno);
		// 1번 로직이 성공할때만 2번 로직을 실행하고 실패하면 롤백 후 널 값 리턴
		commit(conn);
	}else {
		rollback(conn);
	}
	
	close(conn);
		
	return notice;
}

public int updateNotice(Notice notice) {
	Connection conn = getConnection();
	
	int result = noticeDao.updateNotice(conn, notice);
	
	if(result > 0) {
		commit(conn);
	} else {
		rollback(conn);
	}
	
	close(conn);
	
	return result;
}

public int delectNotice(int nno) {
	Connection conn = getConnection();
	Notice notice = null;
	
	int result = noticeDao.deleteNotice(conn, nno);
	
	if(result> 0) {
		commit(conn);
	}else {
		rollback(conn);
	}
	
	close(conn);
		
	return result;
}

}