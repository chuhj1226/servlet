package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class BoardUpateServlet
 */
@WebServlet("/board/update")
public class BoardUpateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* bid, cid, btitle, bcontent 담은 Baord 객체로
		 * DB update (수정 일자도 변경) */
		int bid = Integer.parseInt(request.getParameter("bid"));
		int cid = Integer.parseInt(request.getParameter("category"));
		String btitle = request.getParameter("title");
		String bcontent = request.getParameter("content");
		
		Board board = new Board();
		board.setBid(bid);
		board.setCid(cid);
		board.setBtitle(btitle);
		board.setBcontent(bcontent);
		
		int result = new BoardService().updateBoard(board);
		/* 성공 시 성공 메세지 alert 후 수정 된 상세페이지
		 * 실패 시 실패 메세지 가지고 에러 페이지*/
		if(result > 0) {
			request.getSession().setAttribute("message", "게시글이 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/board/detail?bid="+bid);
		} else {
			
			request.setAttribute("message", "수정에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		}
	}

}
