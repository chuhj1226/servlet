<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>menubar2</title>
<!-- 구글웹폰트 -->
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 외부 스타일 시트 -->
<link href="<%= request.getContextPath() %>/resources/css/menubar-style.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
	<header id="header">
		<!-- 로고 이미지를 클릭하면 첫 화면으로 -->
		<a href="<%= request.getContextPath() %>">
		<%-- request.getContextPath() => jsp --%>
			<img class="logo" src="<%= request.getContextPath() %>/resources/images/logo.jpg">
		</a>
		<div class="btnArea">
			<a href="#">회원가입</a>
			<a href="<%= request.getContextPath()%>/login">로그인</a>
		</div>
	</header>
	<nav id="nav">
		<ul>
			<li><a href="<%= request.getContextPath() %>">HOME</a></li>
			<li><a href="#">공지사항</a></li>
			<li><a href="#">게시판</a></li>
			<li><a href="#">사진게시판</a></li>
		</ul>
	</nav>
</div>

</body>
</html>