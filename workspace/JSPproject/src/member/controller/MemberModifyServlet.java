package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberModifyServlet
 */
@WebServlet("/memberModify")
public class MemberModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 정보 수정을 눌렀을 때 정보 수정 화면으로 이동
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/memberModifyForm.jsp");
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청에 넘어온 수정할 회원 정보를 DB에서 update 후 화면 응답
		
		// 1. 한글 값 인코딩(필터 적용 후 주석)
//		request.setCharacterEncoding("UTF-8");
		
		// 2. 회원정보 수정에 필요한 값 추출
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String[] addressArr = request.getParameterValues("address");
		
		String[] interestArr = request.getParameterValues("interest");
		
		String address = "";
		String interest = "";
		
		if(addressArr != null && !addressArr[0].equals("")) {
			address = String.join("|",addressArr);
		}
		
		if(interestArr != null) {
			interest = String.join("|", interestArr);
		}
		
		Member member = new Member(userNo, userName, phone, email, address, interest);
	
		System.out.println("수정할 정보 : "+member);
		// 3. 비즈니스 로직 수행 (DB update)
		// 개인정보 수정 후에 session에 저장 된 loginUser 객체의 정보도 수정 되어야
		// 수정 후 다시 개인정보 수정 화면을 요청했을 때 수정사항이 반영 됨
		// DB update -> 수정 된 member select -> session의 loginUser 값 변경
		Member updatedMember = new MemberService().updateMember(member);
		
//		System.out.println("수정 된 정보 : " +updatedMember);
	
		// 4. 응답 화면 작성
		/* 회원 정보 수정 성공 시 */
		if(updatedMember != null) {
			// 메뉴바에 작성 된 코드를 이용하여 message 저장하여 alert 처리
			request.getSession().setAttribute("message", "회원 정보 수정이 완료 되었습니다.");
			// 수정 된 값으로 loginUser 변경
			request.getSession().setAttribute("loginUser", updatedMember);			
			// 메인화면으로 redirect
			response.sendRedirect(request.getContextPath());
			
		} else { /* 회원 정보 수정 실패 시 */
			request.setAttribute("message", "회원 정보 수정에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
		}
	}

}
