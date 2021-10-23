package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import wrapper.EncrtyptWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
// 암호화가 필요한 서블릿에만 필터 적용하면 되므로
// 1) LoginServlet, 2) MemberJoinServlet, 3) PwdModifyServlet
// 세 가지 서블릿에만 적용되면 됨
// 매핑 기준은 urlPattern or servletName
@WebFilter(filterName="encrypt", servletNames = {"LoginServlet"
                                    , "MemberJoinServlet"
                                    , "PwdModifyServlet"})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
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
//      System.out.println("암호화 필터입니다.");
	   
	   /* Wrapper : 필터에 넘어온 requset, response를 변경할 때 사용
	    * 			request나 response를 특정한 형태로 감싸서 변형
	    */
	   
	   // 매개변수로 받아온 ServletRequest를 HttpsServletRequest로 다운 캐스팅 하여
	   // Wrapper 객체의 생성자로 전달함
	   EncrtyptWrapper encWrapper = new EncrtyptWrapper((HttpServletRequest)request);
	   
	   // request -> encWrapper
	   // 3개의 요청에 대해서 servlet에서 getParameter 메소드를 호출하게 되면
	   // EncryptWrapper에 오버라이딩 된 getParameter 메소드가 호출 되고
	   // 그 안에서 Password와 관련된 값들은 평문이 아닌 가공된 문자로 처리함
       chain.doFilter(encWrapper, response);
   }

   /**
    * @see Filter#init(FilterConfig)
    */
   public void init(FilterConfig fConfig) throws ServletException {
    
   }

}