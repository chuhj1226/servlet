<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link href="<%= request.getContextPath() %>/resources/css/loginpage-style.css" rel="stylesheet">
</head>
<body>
	<!-- 모든 페이지에 include할 menubar.jsp -->
	<%@ include file="/WEB-INF/views/common/menubar.jsp"%>
	
	<div class="wrapper">
		<div class="outer">
			<form class="loginArea" action="<%= request.getContextPath() %>/login"
			method="post">
				<h1>로그인</h1>
				<h4>아이디</h4>
				<span class="input_area">
					<input type="text" name="userId" id="userId" 
					placeholder="아이디를 입력하세요" required>
				</span>
				<h4>비밀번호</h4>
				<span class="input_area">
					<input type="password" name="userPwd" id="userPwd"
					placeholder="비밀번호를 입력하세요" required>
				</span>
				<h5>
				<input type="checkbox" name="remember" id="remember">
				<label for="remember">아이디 기억하기</label>
				</h5>
				<span class="input_area">
					<input type="submit" value="로그인">
				</span>
			</form>
		</div>
	</div>
	
	
</body>
</html>