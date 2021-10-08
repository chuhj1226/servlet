<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String toppings = (String)request.getAttribute("toppings");
	int total = (int)request.getAttribute("total");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#pizza { color : red; }
	#topping { color : green; }
	#total { color : blue; }	
</style>
</head>
<body>
	<h1>주문 내역</h1>
	<h2>
		피자는 <span id='pizza'><%= request.getParameter("pizza") %></span>, 
		토핑은
		<% if(toppings.equals("")){ %>
		<span id='topping'>추가 없음</span>
		<% } else { %>
		<span id='topping'><%= toppings %></span>
		<% } %>
		으로 주문하셨습니다.
	</h2>
	<h2>총합 : <span id='total'><%= total %></span></h2>
	
	
	
</body>
</html>