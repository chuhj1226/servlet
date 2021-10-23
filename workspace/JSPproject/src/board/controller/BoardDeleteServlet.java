package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteServlet() {
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
		/* bid 가지고 DB update (STATUS 값 'Y' -> 'N') */
		int bid = Integer.parseInt(request.getParameter("bid"));
		
		int result = new BoardService().deleteBoard(bid);
		/* 삭제 성공 시 성공 메세지 ALERT 후 게시판 목록으로
		 * 삭제 실패 시 실패 메세지 가지고 에러페이지로*/
		
		if(result > 0) {
			request.getSession().setAttribute("message", "게시글이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/board/list");
		} else {
			request.setAttribute("message", "게시글 삭제에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		}
	}

}
