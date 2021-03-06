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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 메뉴바의 로그인을 클릭했을 때 로그인 페이지로 이동
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/loginpage.jsp");
		view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// id, pwd를 입력하고 로그인 버튼을 눌렀을 때
		
		// 0. post 방식으로 submit 했을 경우 문자 인코딩 처리 필요하지만
		//    로그인 시 전송 받는 값에는 한글 값이 없으므로 인코딩 처리 하지 않아도 무방함
		//    
		
		// 1. 요청에 포함 된 id, pwd 값 추출
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
//		System.out.println(userId + " " +userPwd);
		
		
		// 2. 비즈니스 로직 처리하는 서비스 클래스의 해당 메소드를 실행 후 처리 결과를 리턴 받음
		// id, pwd를 가지고 DB에 접근해서 일치하는 member가 있을 경우 member 객체를 리턴
		Member loginUser = new MemberService().loginMember(userId,userPwd);
//		 System.out.println(userId+" "+userPwd);
		
		
		System.out.println("loginUser : "+loginUser);
		// 3. 요청에 대한 결과를 통해 응답 화면을 결정
		if(loginUser != null) {
			// id, pwd 값이 일치하는 유저가 있을 경우
		} else {
			// id, pwd 값이 일치하는 유저가 없을 경우 => 로그인에 실패 => errorpage로 forward
			request.setAttribute("message", "로그인에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
