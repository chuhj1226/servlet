package notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/notice/delete")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
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
		/* 요청에 담긴 클라이언트가 입력한 값 추출 (글번호) */
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		int result = new NoticeService().delectNotice(nno);
		/* nno 전달하여 DB update (STATUS 컬럼 'Y'->'N'으로 변경 */
		
		if(result > 0) {
			request.getSession().setAttribute("message", "공지사항이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath()+"/notice/list");
		} else {
			request.setAttribute("message", "공지사항 삭제에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		}
		/* 삭제(수정) 결과에 따라 성공 시
		 * "공지사항이 삭제 되었습니다" alert, 게시글 목록 페이지로
		 * 실패 시
		 * "공지사항 삭제에 실패하였습니다" message, 에러 페이지로  */
	}

}
