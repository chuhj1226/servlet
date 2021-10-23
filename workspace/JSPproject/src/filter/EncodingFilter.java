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

/**
 * Servlet Filter implementation class EncodingFilter
 */
/* 1. 어노테이션을 통한 필터 매핑 
 * urlPattern으로 어떤 요청을 처리하기 전에 필터를 거칠 것인지 지정
 * --> /*로 지정하게 되면 모든 요청을 의미함 */
@WebFilter("/*")
public class EncodingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncodingFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// 컨테이너가 필터 인스턴스를 제거할 때 호출 - 서버 종료
//		System.out.println("필터 인스턴스가 소멸됩니다.");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 컨테이너가 현재 요청에 필터를 적용하겠다 판단 되었을 때 호출 됨
		
		// 서블릿 수행 전 필터 동작
//		System.out.println("doFilter() 동작합니다.");
		
		// place your code here -> 필터링을 통해 수행하고자 하는 코드 작성
		// 전송 방식이 POST 일 때 request에 대해서 인코딩 처리함
		HttpServletRequest hrequest = (HttpServletRequest)request;
		if(hrequest.getMethod().equalsIgnoreCase("post")) {
//			System.out.println("post 전송 시에만 encoding 처리함");
			request.setCharacterEncoding("utf-8");
		}
		// 적용 확인을 위해 memberJoin, memberModify 서블릿의 인코딩 구문 삭제 후 테스트

		// pass the request along the filter chain
		// filterChain의 doFilter()는 다음 필터를 호출하거나, 마지막 필터라면 서블릿으로 넘어감
		chain.doFilter(request, response);
		
		// 서블릿 수행 후 필터 동작
//		System.out.println("doFilter() 이후 처리되는 동작입니다.");
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// 컨테이너가 필터를 인스턴스화 할 때 호출 -> 필터 설정 관련 코드 작성 가능
//		System.out.println("EncodingFilter 초기화 되었습니다.");
	}

}













