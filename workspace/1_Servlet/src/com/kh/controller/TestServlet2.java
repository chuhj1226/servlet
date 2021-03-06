package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet2
 */
@WebServlet("/test2")
public class TestServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet 호출");

		// 0. 인코딩 설정
		// POST 방식으로 넘어온 데이터가 영어/숫자가 아닌 경우 인코딩 처리 필요
		// request 내의 파라미터 값은 기본적으로 ISO-8859-1로 인코딩 되었다고 간주 되기 때문(default 설정)
		request.setCharacterEncoding("UTF-8");

		// 1. 요청 값에 포함 된 데이터 추출
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		String[] foodArr = request.getParameterValues("food");

		// 사용자 요청 값 추출 결과 확인
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);

		// 체크 박스 미체크시 foodArr 배열은 null (NullPointerException 방지를 위해 조건문)
		if (foodArr != null) {
			for (int i = 0; i < foodArr.length; i++) {
				System.out.println("foodArr[" + i + "] : " + foodArr[i]);
			}
		}
		// 2. Service - Dao - DB로 비즈니스 로직 수행 (현재 프로젝트에서는 생략)

		// 3. 요청과 관련된 비즈니스 로직이 다 처리되면 응답 화면 처리

		// 응답 화면에 대한 설정
		// => 응답 화면 문서 형태는 HTML이며 문자 셋은 UTF-8임을 설정함
		response.setContentType("text/html; charset=UTF-8");

		// 사용자에게 응답 화면을 출력하기 위한 스트림(클라이언트와의 연결 된 길) 생성
		// Response 객체의 문자 기반 출력 스트림
		PrintWriter out = response.getWriter();

		// java 코드를 통해 응답할 화면을 작성
		out.println("<html>");
		out.println("<head>");
		out.println("<title>개인정보 출력화면</title>");
		out.println("<style>");
		out.println("h2{color:red;} ");
		out.println("span.name{color:orange; font-weight:bold;}");
		out.println("span.gender{color:yellow; font-weight:bold;}");
		out.println("span.age{color:green; font-weight:bold;}");
		out.println("span.city{color:blue; font-weight:bold;}");
		out.println("span.height{color:navy; font-weight:bold;}");
		out.println("span.food{color:purple; font-weight:bold;}");
		out.println("</style>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h2>개인정보 결과(POST) 화면</h2>");
		out.printf("<span class='name'>%s</span>님은 ", name);
		out.printf("<span class='age'>%s</span>이시며 ", age);
		out.printf("<span class='city'>%s</span>에 사는", city);
		out.printf("키 <span class='height'>%scm의</span>", height);
		out.printf("<span class='gender'>%s</span>입니다.", gender);
		out.print("좋아하는 음식은<span class='food'>");

		if (foodArr != null) {
			for (int i = 0; i < foodArr.length; i++) {
				out.printf("%s ", foodArr[i]);
			}
			out.println("</span>입니다.");
		} else {
			out.println("</span>없습니다.");
		}

		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost 호출");
		doGet(request, response);
	}

}
