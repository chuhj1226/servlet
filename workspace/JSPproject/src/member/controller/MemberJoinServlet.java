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
 * Servlet implementation class MemberJoinServlet
 */
@WebServlet(name="MemberJoinServlet", urlPatterns="/memberJoin")
public class MemberJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberJoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 가입 a 태그를 클릭 했을 때 회원 가입 폼 화면으로 이동
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/memberJoinForm.jsp");
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 정보 입력 후 회원가입 버튼을 눌렀을 때
		// 요청을 통해 넘어온 정보 DB에 Insert 후 응답 화면
		
		// 1. 한글 값이 있을 경우 인코딩 처리
//		request.setCharacterEncoding("UTF-8");
		
		// 2. requset에 담긴 값 꺼내서 변수에 저장
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String[] addressArr = request.getParameterValues("address");
		String[] interestArr = request.getParameterValues("interest");
		
		String address ="";
		String interest = "";
		
		/* 주소 값이 입력 된 경우 문자열 합치기 */
		if(addressArr != null && !addressArr[0].equals("")) {
			address = String.join("|", addressArr);
		}
		/* 관심분야 체크박스가 체크 된 경우 문자열 합치기 */
		if(interestArr != null)
			interest = String.join("|", interestArr);
		
		// 가입 정보를 담을 Member 객체 생성
		Member member = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 3. 비즈니스 로직을 수행할 서비스 메소드로 Member 객체 전달 후 결과 값 리턴 받기
		int result = new MemberService().insertMember(member);
		
		System.out.println(result);
		// 4. 결과 성공/실패 여부에 따라 응답 결정
		if(result > 0) {
			// 메인 화면으로 이동 후 alert로 "회원가입이 완료되었습니다. 로그인 해주세요." 처리
			// sendRedirect 처리하면 request 객체가 다시 만들어지므로 request.setAttribute는 소용이 없음
			// session 객체에 해당하는 메세지 담기
			request.getSession().setAttribute("message", "회원 가입이 완료 되었습니다. 로그인 해주세요.");
			// 메인 화면으로 이동, 서버 재요청(sendRedirect)
			// forward 처리 시 /memberJoin에 대한 요청 남아있음
			response.sendRedirect(request.getContextPath());
		}else {
			// 회원 가입에 실패했을 경우 error 페이지로 이동
			request.setAttribute("message", "회원 가입에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
