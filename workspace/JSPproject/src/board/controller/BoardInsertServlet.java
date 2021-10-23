package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import member.model.vo.Member;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class BoardInsertServlet
 */
@WebServlet("/board/insert")
public class BoardInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/board/boardInsertView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 요청에 담긴 데이터 추출 Board 타입 객체에 설정
		 * (cid, btitle, bcontent, bwriter, btype) */
		int cid = Integer.parseInt(request.getParameter("category"));
		String btitle = request.getParameter("title");
		String bcontent = request.getParameter("content");
		int bwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		int btype = Integer.parseInt( request.getParameter("type"));
		
		Board board = new Board();
		board.setCid(cid);
		board.setBtitle(btitle);
		board.setBcontent(bcontent);
		board.setBwriter(bwriter);
		board.setBtype(btype);
		
		int result = new BoardService().insertBoard(board);
		
	
		
		/* Board 테이블 insert */
		
		/* 성공 시 "게시글 등록이 완료되었습니다" alert 후 게시글 목록으로*/
		
		/* 실패 시 "게시글 등록에 실패하였습니다" message 설정하여 에러페이지로 */
		if(result > 0) {
			// 공지사항 목록 화면을 응답하고자 함
			// noticeListView.jsp로 직접 forward 처리하면 공지사항 목록 조회가 누락되므로
			// 새로 갱신 된 공지사항 목록을 불러오는 NoticeListServlet을 다시 수행해야 함(/notice/list 서버에 재호출)
			request.getSession().setAttribute("message", "게시글 등록이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/board/list");
//			request.getRequestDispatcher("/WEB-INF/views/notice/noticeListView.jsp").forward(request, response);	이렇게쓰면 안댐 게시글이안뜸
		
		} else {
			request.setAttribute("message", "게시글 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		
		}
	}

}
