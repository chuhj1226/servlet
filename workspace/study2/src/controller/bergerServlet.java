package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class bergerServlet
 */
@WebServlet("/confirmBerger")
public class bergerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bergerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String berger = request.getParameter("berger");
		String[] side = request.getParameterValues("side");
		
		int total = 0; // 결제 총액
		String sides = "";    // 토핑 문자열 합치기
		String bergers = "";
		
	
		
	if(berger != null) {
		bergers = berger;
		switch(berger) {
		case "불고기버거" : total += 5000; break;
		case "치즈버거" : total += 6000; break;
		case "상하이버거" : total += 7000; break;
		case "빅맥" : total += 8000; break;
		case "싸이버거" : total += 9000; break;
		}
	}
	
	
	
	if(side != null) {
		sides = String.join(", ", side);				
		
		for(int i = 0 ; i < side.length; i++) {
			switch(side[i]) {
			case "양념감자" : total += 1000; break;
			case "아이스크림" : total += 1500; break;
			case "맥너겟" : total += 2000; break;
			case "롱치즈" : total += 2000; break;
			case "치즈스틱" : total += 2000; break;
			case "치즈볼" : total += 3000; break;
			
			}
		}
	}
	
	
	request.setAttribute("sides", sides);
	request.setAttribute("total", total);
	request.setAttribute("bergers", bergers);

	
	
	RequestDispatcher view = request.getRequestDispatcher("views/bergerPayment.jsp");
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
