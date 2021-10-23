package board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Search;

/**
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // a 태그로 url 요청 = get 방식
      // * page : 현재 요청 페이지
      // 기본적으로 게시판은 1페이지부터 시작
      int page = 1;
      
      // 하지만 페이지 전환 시 전달 받은 현재 페이지가 있을 경우 해당 페이지를 page로 적용 (page 파라미터에 값이 존재)
      if(request.getParameter("page") != null) {   // board/list?page=5 넘어오면 5로 요청
         page = Integer.parseInt(request.getParameter("page"));
      }
      
      // 검색 관련 파라미터 추출
      String searchCondition = request.getParameter("searchCondition");
      String searchValue = request.getParameter("searchValue");
      
      
      
      
      
      // 페이징과 관련 된 데이터, 조회된 boardList를 map에 담아 리턴
      Map<String, Object> map 
      = new BoardService().selectList(page, new Search(searchCondition, searchValue));
      
      request.setAttribute("pi", map.get("pi"));
      request.setAttribute("boardList", map.get("boardList"));
      
      RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/board/boardListView.jsp");
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