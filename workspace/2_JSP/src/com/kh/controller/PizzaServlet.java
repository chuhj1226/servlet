package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PizzaServlet
 */
@WebServlet("/confirmPizza")
public class PizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청에 전달 된 값에 한글이 있을 경우를 대비하여 인코딩 작업 필요
		request.setCharacterEncoding("UTF-8");
		
		// 요청에 포함 된 값 추출
		String pizza = request.getParameter("pizza");
		String[] topping = request.getParameterValues("topping");
		
//		System.out.println("pizza : "+pizza);
//		for(int i = 0 ; i <topping.length; i++) {
//			System.out.println("topping["+i+"] : "+topping[i]);
//		}
	
	int total = 0; // 결제 총액
	String toppings = "";    // 토핑 문자열 합치기
	switch(pizza) {
	case "치즈피자" : total += 5000; break;
	case "콤비네이션피자" : total += 6000; break;
	case "포테이토피자" : total += 7000; break;
	case "고구마피자" : total += 8000; break;
	case "불고기피자" : total += 9000; break;
	}
	
	// 체크박스 미 체크시 null 이므로 NullPointerException 방지 조건문
	if(topping != null) {
		toppings = String.join(", ", topping);
		
		for(int i = 0 ; i < topping.length; i++) {
			switch(topping[i]) {
			case "고구마무스" : total += 1000; break;
			case "콘크림무스" : total += 1500; break;
			case "파인애플토핑" : total += 2000; break;
			case "치즈토핑" : total += 2000; break;
			case "치즈크러스트" : total += 2000; break;
			case "치즈바이트" : total += 3000; break;
			
			}
		}
		
	}
	
	// 별도의 JSP에 프레젠테이션 로직(응답 화면 처리) 구현할 예정이므로
	// request 객체에 setAttribute 메소드를 통해 값 저장
	request.setAttribute("toppings", toppings);
	request.setAttribute("total", total);
	
	// 위임 JSP path 설정하여 RequestDispatcher 객체로 forward 처리
	RequestDispatcher view = request.getRequestDispatcher("views/05_pizzaPayment.jsp");
	view.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
