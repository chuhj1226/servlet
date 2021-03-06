/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.71
 * Generated at: 2021-10-05 14:27:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class bergerOrderFrom_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>햄버거 주문</title>\r\n");
      out.write("<style>\r\n");
      out.write("	table {\r\n");
      out.write("		border : 1px solid black;\r\n");
      out.write("		border-collapse : collapse;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	th, td {\r\n");
      out.write("		border : 1px solid black;\r\n");
      out.write("		padding : 10px;\r\n");
      out.write("		text-align : center;\r\n");
      out.write("	}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<h1>메뉴</h1>\r\n");
      out.write("	<form action=\"/study2/confirmBerger\" method=\"post\">\r\n");
      out.write("		<table>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th width=\"50\">종류</th>\r\n");
      out.write("				<th>이름</th>\r\n");
      out.write("				<th>가격</th>\r\n");
      out.write("				<th>체크</th>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td rowspan=\"5\">햄버거</td>\r\n");
      out.write("				<td>불고기버거</td>\r\n");
      out.write("				<td>5000</td>\r\n");
      out.write("				<td><input type=\"radio\" name=\"berger\" value=\"불고기버거\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>치즈버거</td>\r\n");
      out.write("				<td>6000</td>\r\n");
      out.write("				<td><input type=\"radio\" name=\"berger\" value=\"치즈버거\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>상하이버거</td>\r\n");
      out.write("				<td>7000</td>\r\n");
      out.write("				<td><input type=\"radio\" name=\"berger\" value=\"상하이버거\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("				<tr>\r\n");
      out.write("				<td>빅맥</td>\r\n");
      out.write("				<td>8000</td>\r\n");
      out.write("				<td><input type=\"radio\" name=\"berger\" value=\"빅맥\"></td>		\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>싸이버거</td>\r\n");
      out.write("				<td>9000</td>\r\n");
      out.write("				<td><input type=\"radio\" name=\"berger\" value=\"싸이버거\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td rowspan=\"6\">사이드메뉴</td>\r\n");
      out.write("				<td>양념감자</td>\r\n");
      out.write("				<td>1000</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"양념감자\"></td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>아이스크림</td>\r\n");
      out.write("				<td>1500</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"아이스크림\"></td>	\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>맥너겟</td>\r\n");
      out.write("				<td>2000</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"파인애플 토핑\"></td>	\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>롱치즈</td>\r\n");
      out.write("				<td>2000</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"롱치즈\"></td>	\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>치즈스틱</td>\r\n");
      out.write("				<td>2000</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"치즈스틱\"></td>	\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<td>치즈볼</td>\r\n");
      out.write("				<td>3000</td>\r\n");
      out.write("				<td><input type=\"checkbox\" name=\"side\" value=\"치즈볼\"></td>	\r\n");
      out.write("			</tr>\r\n");
      out.write("		</table>\r\n");
      out.write("		<input type=\"submit\" value=\"주문하기\">\r\n");
      out.write("	</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
