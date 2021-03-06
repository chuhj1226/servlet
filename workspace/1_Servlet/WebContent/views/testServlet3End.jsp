<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 
	이 페이지의 언어는 java 언어이며
	contentType을 통해 문서 형식은 html이고 문자셋은 UTF-8이다를 알리는 부분
 -->
<%
	// <% 스크립틀릿이라고 하며 이 영역 안에는 java 코드를 작성할 수 있음
	// TestServlet3에서 request 객체에 setAttribute를 이용해 값을 저장했으므로
	// getAttribute를 이용해 저장 된 값을 추출할 수 있음
	String name = (String)request.getAttribute("name");
	String age = (String)request.getAttribute("age");
	String city = (String)request.getAttribute("city");
	String height = (String)request.getAttribute("height");
	String gender = (String)request.getAttribute("gender");
	String foods = (String)request.getAttribute("foods");
%>
<!-- 
	getParameter
		클라이언트의 HTML 페이지에서 필요한 정보를 얻는데 사용
		웹 브라우저에서 전송 받은 requset 영역에서 name 값이 같은 것을 찾아 값을 읽어옴(일치 값 없으면 null)
		항상 String 반환
	getAttribute
		이전의 다른 Servlet 또는 JSP 페이지에서 설정 된 값을 가져오는데 사용
		request.setAttribute("name", Object) 에서 설정 된 name과 일치하는 값을 가져옴 (일치 값 없으면 null)
		항상 Object 반환
	setAttribute
		다른 Servlet 또는 JSP 페이지로 forward 할 경우 속성 값을 저장하기 위해 사용
 -->
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2 { color : red;}
	span.name { color : orange; font-weight: bold;}
	span.gender { color : yellow; font-weight: bold;}
	span.age { color : green; font-weight: bold;}
	span.city { color : blue; font-weight: bold;}
	span.height { color : navy; font-weight: bold;}
	span.food { color : purple; font-weight: bold;}
</style>
</head>
<body>

	<h2>개인 정보 결과 (Servlet+JSP)</h2>
	<span class='name'><%= name %></span> 님은
	<span class='age'><%= age %></span>이시며, 
	<span class='city'><%= city %></span>에 사는
	키 <span class='height'><%= height %>cm</span>인 
	<span class='gender'><%= gender %></span>입니다. 
	좋아하는 음식은 <span class='food'><%= foods %></span>입니다.
	
</body>
</html>