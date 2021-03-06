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
 * Servlet implementation class LoginServlet
 */
@WebServlet(name="LoginServlet", urlPatterns="/login")
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
		
		// 1. 요청에 포함 된 id, pwd 값 추출
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		System.out.println(userId + " " +userPwd);
		
		// System.out.println(userId+" "+userPwd);
		
		// 2. 비즈니스 로직 처리하는 서비스 클래스의 해당 메소드를 실행 후 처리 결과를 리턴 받음
		// id, pwd를 가지고 DB에 접근해서 일치하는 member가 있을 경우 member 객체를 리턴
		Member loginUser = new MemberService().loginMember(userId,userPwd);
		
//		System.out.println("loginUser : "+loginUser);
		
		// 3. 요청에 대한 결과를 통해 응답 화면을 결정
		if(loginUser != null) {
			// id, pwd 값이 일치하는 유저가 있을 경우
			
			/* loginUser에 대한 정보를 어딘가에 담아서 유지해야 하는데 담을 수 있는 공간은 다음과 같음
			 * 1. application : 어플리케이션 단위
			 * 2. session : 브라우저 단위
			 * 3. request : 요청 단위
			 * 4. page : 해당 페이지 내
			 * 
			 * 위의 4개의 영역 모두 setAttribute(name, object)를 이용해 객체를 저장할 수 있음
			 * 꺼낼 때는 getAttribute(name)
			 * 삭제할 때는 removeAttribute(name)
			 * 
			 * Session 객체 : 웹 브라우저 당 하나씩 존재하는 객체로 Session에 로그인 한 회원 객체를 등록해놓으면
			 * 어떤 페이지에서든 Session에 담긴 회원 객체에 대한 정보를 사용할 수 있음
			 */
			
			// 해당 클라이언트에 대한 세션 객체 가져오기
			HttpSession session = request.getSession();
			
			// 세션 객체에 로그인한 유저의 정보를 담음
			// => 세션이 유지되는 동안 어디에서든 loginUser의 정보를 알 수 있음
			session.setAttribute("loginUser", loginUser);
			
			// 초 단위로 해당 세션의 유효 시간 설정 가능
			// 10분 뒤 세션이 유효하지 않아져서 자동 로그아웃이 됨
			session.setMaxInactiveInterval(600);
			
			// 로그인 완료 후 다시 메인 페이지로 이동하기 위해
			// sendRedirect 호출(서버로 재요청) -> /login 요청은 사라짐
			// request.getContextPath() => /jsp
			response.sendRedirect(request.getContextPath());
			
			/* forward
			 *  : 해당 요청에 대한 응답은 JSP 페이지에 위임
			 * 	    기존 requset, response 객체 유지
			 * 	  url창에 기존 요청 값 남아 있음
			 * -----------------------------------
			 * redirect
			 *  : 서버에 다시 새로운 요청을 보냄
			 * 	    새로운 request, response 객체 생성
			 *    url창 새로운 요청 값으로 변경
			 */
		} else {
			// id, pwd 값이 일치하는 유저가 없을 경우 => 로그인에 실패 => errorpage로 forward
			request.setAttribute("message", "로그인에 실패하였습니다.");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp");
			view.forward(request, response);
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
