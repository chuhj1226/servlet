package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/accountDelete")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 탈퇴 유저에 대해서 처리할 수 있는 값 추출 */
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		/* DB에서 status 값 Y -> N으로 update
		 * MemberService 클래스에 deleteAccount 라는 메소드 호출
		 * */
		int result = new MemberService().deleteAccount(userNo);

		if(result > 0) {
			/* 수행 결과에 따라서 성공한 경우 로그인 세션 정보 삭제 후 메인 페이지로 이동 
			 *  "회원 탈퇴가 완료 되었습니다." alert 처리
			 *  */
			request.getSession().removeAttribute("loginUser");
			request.getSession().setAttribute("message","회원 탈퇴가 완료 되었습니다.");
			response.sendRedirect(request.getContextPath());
		}else {
			/* 실패한 경우 "회원 탈퇴에 실패하였습니다" 메세지 가지고 에러 페이지로 이동 */
			request.getSession().setAttribute("message", "회원 탈퇴에 실패하였습니다");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
