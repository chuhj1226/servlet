<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="member.model.vo.Member"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// session 객체에 담긴 loginUser 정보를 변수에 담아두기
	Member loginUser = (Member)session.getAttribute("loginUser");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar</title>
<!-- 구글웹폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 외부 스타일 시트 -->
<link href="<%= request.getContextPath() %>/resources/css/menubar-style.css" rel="stylesheet">
<%-- session에 담긴 message 있을 경우 alert 하는 script --%>
<% if(session.getAttribute("message")!= null) { %>
<script>
	alert('<%= session.getAttribute("message") %>');
</script>
<% session.removeAttribute("message");
	}
%>



</head>
<body>
<c:set var="contextPath" value="${ pageContext.servletContext.contextPath }"
 scope="application"/>
<div class="wrapper">
	<header id="header">
		<!-- 로고 이미지를 클릭하면 첫 화면으로 -->
		<a href="<%= request.getContextPath() %>">
		<%-- request.getContextPath() => jsp --%>
			<img class="logo" src="<%= request.getContextPath() %>/resources/images/logo.jpg">
		</a>
		<div class="btnArea">
			<% if(loginUser == null) { %>
			<a href="<%= request.getContextPath()%>/memberJoin">회원가입</a>
			<a href="<%= request.getContextPath()%>/login">로그인</a>
			<% } else { %>
			<a href="<%= request.getContextPath()%>/memberModify">정보수정</a>|
			<a href="<%= request.getContextPath()%>/logout">로그아웃</a>
			<% } %>
		</div>
	</header>
	<nav id="nav">
		<ul>
			<li><a href="<%= request.getContextPath() %>">HOME</a></li>
			<li><a href="${ contextPath }/notice/list">공지사항</a></li>
			<li><a href="${ contextPath }/board/list">게시판</a></li>
			<li><a href="${ contextPath }/gallery/list">사진게시판</a></li>
		</ul>
	</nav>
</div>

</body>
</html>