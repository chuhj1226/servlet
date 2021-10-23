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
 * Servlet implementation class NoticeUpdateServlet
 */
@WebServlet("/notice/update")
public class NoticeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeUpdateServlet() {
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
		/* 요청에 담긴 클라이언트가 입력한 값 추출 (글제목, 글내용) +) ... */
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int nno = Integer.parseInt(request.getParameter("nno"));
		
		/* Notice 객체 전달하여 DB update */
		Notice notice = new Notice();
		notice.setNtitle(title);
		notice.setNcontent(content);
		notice.setNno(nno);
		
		int result = new NoticeService().updateNotice(notice);
		

		
		if(result > 0) {
			
			request.getSession().setAttribute("message", "공지사항이 수정이 완료되었습니다.");
			response.sendRedirect(request.getContextPath()+"/notice/detail?nno="+nno);
		} else {
			
			request.setAttribute("message", "공지사항 수정에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		}
		/* 수정 결과에 따라 성공 시
		 * "공지사항이 수정 완료되었습니다" alert, 해당 게시글 상세 페이지로
		 * 실패 시
		 * "공지사항 수정에 실패하였습니다" message, 에러 페이지로  */
		
	}

}
