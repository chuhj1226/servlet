<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" errorPage="error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02_directivePage</title>
</head>
<body>
	<%
		ArrayList<String> list = new ArrayList<>();
		// 지시어 page 속성 중 import 속성을 통해 선언
		
		list.add("HTML");
		list.add("Servlet");
		list.add("JSP");
		
		// 의도적으로 오류를 발생시키는 코드
		list.add(10,"오류날껄");
		// 500 에러 : 서버코드 상의 문제 
		// 404 에러 : 경로 문제
		
		Date today = new Date();
		
		
		
	%>
	
	<p>리스트 길이 : <%= list.size() %></p>
	<p>오늘 날짜 : <%= today %></p>
	
	<%--
		에러 페이지 컨트롤 방법
		1) page 지시자 errorPage를 이용한 방법 -> error.jsp 만들기
		2) web.xml에 error-page 태그를 등록하는 방법 -> 404error.jsp 만들기
	 --%>

</body>
</html>