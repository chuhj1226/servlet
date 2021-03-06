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
 * Servlet implementation class PwdModifyServelt
 */
@WebServlet(name="PwdModifyServlet",urlPatterns="/pwdModify")
public class PwdModifyServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdModifyServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/pwdModifyForm.jsp");
    	view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청에 담긴 값 추출
		String userPwd = request.getParameter("userPwd"); // 현재 비밀번호
		String newPwd = request.getParameter("newPwd");	  // 변경할 비밀번호
		// 세션에 저장 된 loginUser를 통해 userNo 추출
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
	
		// 비즈니스 로직 수행 (DB update)
		  Member updatedMember = new MemberService().updatePwd(userNo, userPwd, newPwd);
		
		System.out.println(updatedMember);
		
		if(updatedMember != null) {
			// 비밀번호 수정이 잘 되었음을 result success로 표시
			request.setAttribute("result", "success");
			// 수정 된 loginUser 다시 session에 저장
			request.getSession().setAttribute("loginUser", updatedMember);
		} else {
			// 비밀번호 수정에 실패했음을 result fail로 표시
			request.setAttribute("result", "fail");
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/pwdModifyForm.jsp");
		view.forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
