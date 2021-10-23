package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import member.model.vo.Member;

/**
 * Servlet Filter implementation class LoginCheckFilter
 */
@WebFilter("/*")
public class LoginCheckFilter implements Filter {
   // 로그인 한/안한 상태 구분
   private List<String> permitList;
   private List<String> resourceList;
    /**
     * Default constructor. 
     */
    public LoginCheckFilter() {
        // TODO Auto-generated constructor stub
    }

   /**
    * @see Filter#destroy()
    */
   public void destroy() {
      // TODO Auto-generated method stub
   }

   /**
    * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletRequest req = (HttpServletRequest)request;   // 다운캐스팅
      String uri = req.getRequestURI();
     // System.out.println(uri);   // 요청한 주소가 뭔지 출력 [shift + F5]
      
      /* (로그인 없이) 요청이 허가된 요청 값 리스트에 현재 요청이 포함되지 않았을 때 */
      if(!permitList.contains(uri)) {
         boolean isResourceFile = false;
         /* 요청 안에 "/resources/" 라는 문자열을 포함하고 있는지 확인 */
         for(String str : resourceList) {
            if(uri.contains(str)) {
               isResourceFile = true;
               break;
            }
         }
         
         /* 허가 리스트에도 없는 요청이면서 리소스 파일이 아닌 경우 */
         if(!isResourceFile) {
            /* 로그인 상태 확인 */
            Member loginUser = (Member)req.getSession().getAttribute("loginUser");
            if(loginUser == null) {
               req.setAttribute("message", "올바르지 않은 요청입니다.");
               req.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
               return;   // chain과 동시에 일어나면 오류남. 둘 중에 하나 결과 응답
            }
            
         }
      }
      
      chain.doFilter(request, response);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
      permitList = new ArrayList<String>();
      permitList.add("/jsp/");
      permitList.add("/jsp/login");
      permitList.add("/jsp/memberJoin");
      permitList.add("/jsp/notice/list");
      permitList.add("/jsp/notice/detail");
      permitList.add("/jsp/board/list");
      permitList.add("/jsp/gallery/list");
      
      resourceList = new ArrayList<String>();
      resourceList.add("/resources/");
      
   }

}