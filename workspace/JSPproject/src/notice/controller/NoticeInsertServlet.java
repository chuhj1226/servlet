package notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.vo.Member;
import notice.model.service.NoticeService;
import notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeInsertServlet
 */
@WebServlet("/notice/insert")
public class NoticeInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/notice/noticeInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = ((Member)request.getSession().getAttribute("loginUser")).getUserId();
		
//		Notice 객체 생성 및 값 설정
		Notice notice = new Notice();
		notice.setNtitle(title);
		notice.setNcontent(content);
		notice.setNwriter(writer);
//		비즈니스 로직 설정
		int result = new NoticeService().insertNotice(notice);
		
//		비즈니스 로직 수행 결과에 따른 응답 처리
		if(result > 0) {
			// 공지사항 목록 화면을 응답하고자 함
			// noticeListView.jsp로 직접 forward 처리하면 공지사항 목록 조회가 누락되므로
			// 새로 갱신 된 공지사항 목록을 불러오는 NoticeListServlet을 다시 수행해야 함(/notice/list 서버에 재호출)
			request.getSession().setAttribute("message", "공지사항이 성공적으로 등록되었습니다.");
			response.sendRedirect(request.getContextPath()+"/notice/list");
//			request.getRequestDispatcher("/WEB-INF/views/notice/noticeListView.jsp").forward(request, response);
			// 위에처럼쓰면 게시글안보임
		
		} else {
			request.setAttribute("message", "공지사항 등록에 실패하였습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);	
		
		}
		
	}

}
