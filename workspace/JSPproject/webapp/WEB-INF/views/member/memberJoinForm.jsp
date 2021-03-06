<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style>
	.outer{
		width:45%;
		min-width : 650px;
		border: 1px solid lightgray;
    	border-radius: 10px;
		margin: 70px auto;
	}
	
	#joinForm {
		width : 400px;
		margin: auto;
		padding: 10px;
	}
	
	#joinForm h1 {
		text-align:center;
	}
	

	.input_area {
	    border: solid 1px #dadada;
	    padding : 10px 10px 14px 10px;
	    background : white;
	}
	
	.input_area input:focus {
		outline: none;
	}
	
	.input_area input:not([type=checkbox]) {
		width : 250px;
		height : 30px;
		border: 0px;
	}
	
	.btnArea {
		text-align:center;
		padding : 50px;
	}
	
	button {
		width : 100px;
		height : 35px;
		border : 0px;
		color:white;
		background:#282A35;
		margin : 5px;
		cursor : pointer;
	}
	
</style>
</head>
<body>
	
	<%@ include file="/WEB-INF/views/common/menubar.jsp" %>

	<div class="outer">
		<div id="joinInfoArea">
			<form id="joinForm" action="<%= request.getContextPath() %>/memberJoin"
			method="post" onsubmit="return validate();">
				<h1>회원 가입</h1>
				
				<h4>* 아이디</h4>
				<span class="input_area"><input type="text" maxlength="13" name="userId" required></span>
				<button id="idCheck" type="button">중복확인</button>
				
				<h4>* 비밀번호</h4>
				<span class="input_area"><input type="password" maxlength="15" name="userPwd" required></span>
				
				<h4>* 비밀번호 확인</h4>
				<span class="input_area"><input type="password" maxlength="15" name="userPwd2" required></span>
				<label id="pwdResult"></label>
				
				<h4>* 이름</h4>
				<span class="input_area"><input type="text" maxlength="5" name="userName" required></span>
				
				<h4>연락처</h4>
				<span class="input_area"><input type="tel" maxlength="11" name="phone"
										placeholder="(-없이)01012345678"></span>
										
				<h4>이메일</h4>
				<span class="input_area"><input type="email" name="email"></span>
				
				<h4>우편번호</h4>
				<span class="input_area"><input type="text" name="address" class="postcodify_postcode5" readonly></span>
				<button type="button" id="postcodify_search_button">검색</button>
				<h4>도로명주소</h4>
				<span class="input_area"><input type="text" name="address" class="postcodify_address" readonly></span>
				<h4>상세주소</h4>
				<span class="input_area"><input type="text" name="address" class="postcodify_details"></span>
				
				<h4>관심분야</h4>
				<span class="input_area">
					<input type="checkbox" id="sports" name="interest" value="운동">
					<label for="sports">운동</label>
					<input type="checkbox" id="climbing" name="interest" value="등산">
					<label for="climbing">등산</label>
					<input type="checkbox" id="fishing" name="interest" value="낚시">
					<label for="fishing">낚시</label>
					<input type="checkbox" id="cooking" name="interest" value="요리">
					<label for="cooking">요리</label>
					<input type="checkbox" id="game" name="interest" value="게임">
					<label for="game">게임</label>
					<input type="checkbox" id="etc" name="interest" value="기타">
					<label for="etc">기타</label>
				</span>
				<div class="btnArea">
					<button id="joinBtn">가입하기</button>
				</div>
			</form>
		</div>
	</div>
	<!-- jQuery와 Postcodify를 로딩한다 -->
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="//d1p7wdleee1q2z.cloudfront.net/post/search.min.js"></script>
	
	<!-- "검색" 단추를 누르면 팝업 레이어가 열리도록 설정한다 -->
	<script> $(function() { $("#postcodify_search_button").postcodifyPopUp(); }); </script>

	<script>
		// 사용자 입력 값 유효성 검사용 함수
		function validate() {
			return true;
		}
	</script>
</body>
</html>










