package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet3
 */
@WebServlet("/test3")
public class TestServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글을 위한 인코딩 지정
		request.setCharacterEncoding("UTF-8");
		
		// 요청에 담긴 값 추출
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		String city = request.getParameter("city");
		String height = request.getParameter("height");
		
		String[] foodArr =request.getParameterValues("food");
		
		// JSP에게 응답 화면 
		// request 객체의 속성에 넘어온 값을 저장함
		request.setAttribute("name", name);
		request.setAttribute("gender", gender);
		request.setAttribute("age", age);
		request.setAttribute("city", city);
		request.setAttribute("height", height);
		
		String foods = "";
		if(foodArr != null) {
			foods = String.join(", ", foodArr); // ", "기준으로 foodArr 배열에 담긴 문자열 하나로 합치기
		}else {
			foods = "없음";
		}
		
		request.setAttribute("foods", foods);
		
		// 위임 객체 : RequestDispatcher 클래스
		RequestDispatcher view = request.getRequestDispatcher("views/testServlet3End.jsp");
		// forward : 기존 요청 정보를 유지하며 페이지 전환
		view.forward(request, response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
