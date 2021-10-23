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
 * Servlet implementation class NoticeUpdateViewServlet
 */
@WebServlet("/notice/updateView")
public class NoticeUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateViewServlet() {
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
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		// 공지사항 상세페이지 조회에서 구현한 기능 활용
		Notice notice = new NoticeService().selectNotice(nno);
	
		String page = "";
		if(notice != null) {
			request.setAttribute("notice", notice);
			page = "/WEB-INF/views/notice/noticeUpdateView.jsp";
		} else {
			request.setAttribute("message", "공지사항 수정 페이지 이동에 실패하였습니다.");
			page = "/WEB-INF/views/common/errorpage.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
