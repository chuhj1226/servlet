package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/notice/detail")
public class NoticeDetailServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int nno = Integer.parseInt(request.getParameter("nno"));
        
        Notice notice = new NoticeService().selectNotice(nno);
        
        String page = "";
        if(notice != null) {
           request.setAttribute("notice", notice);
           page = "/WEB-INF/views/notice/noticeDetailView.jsp";
        }else {
           request.setAttribute("message", "공지사항 상세페이지를 불러오는데 실패하였습니다.");
           page = "/WEB-INF/views/common/errorpage.jsp";
        }
        
        request.getRequestDispatcher(page).forward(request, response);
     }
   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   }

}