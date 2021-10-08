<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  errorPage="error.jsp"%>
<%
	String sides = (String)request.getAttribute("sides");
	int total = (int)request.getAttribute("total");
	String berger = (String)request.getAttribute("bergers");

	String bergers = (String)request.getParameter("berger");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#berger { color : red; }
	#side { color : green; }
	#total { color : blue; }	
</style>
</head>
<body>
	<h1>주문 내역</h1>
	<h2>
		햄버거는 
		<% if(bergers == null){ %>
		<span id='berger'>추가 없음</span>
		<% } else { %>
		<span id='berger'><%= bergers %></span>
		<% } %>, 
		
		사이드는
		<% if(sides.equals("")){ %>
		<span id='side'>추가 없음</span>
		<% } else { %>
		<span id='side'><%= sides %></span>
		<% } %>
		으로 주문하셨습니다.
	</h2>
	<h2>총합 : <span id='total'><%= total %></span></h2>
	<% System.out.println(bergers); %>
	<% System.out.println(berger); %>
</body>
</html>