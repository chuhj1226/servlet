<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<style>
.outer {
	width: 800px;
	margin: auto;
}

.wrap {
	width: 780px;
	margin: 100px auto;
}

.board_title {
	border-bottom: 1px solid #282A35;
}

.board_content {
	padding: 0px 20px;
}

.board_content .content {
	margin-bottom: 30px;
}

.input_area {
	border: solid 1px #dadada;
	padding: 10px 10px 14px 10px;
	background: white;
}

.input_area select {
	width: 150px;
	height: 30px;
	border: 0px;
}

.input_area input {
	width: 700px;
	height: 30px;
	border: 0px;
}

.input_area input:focus, .input_area select:focus {
	outline: none;
}

.textarea {
	resize: none;
	border: solid 1px #dadada;
}

.textarea:focus {
	outline: none;
}

.title_span {
	background-color: #282A35;
}

.board_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
	cursor: pointer;
}

.btn_area {
	text-align: center;
	border-top: 1px solid #282A35;
	padding: 30px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/views/common/menubar.jsp" />
	<div class="outer">
		<div class="wrap">
			<div class="board_area">
				<div class="board_title">
					<h1>게시글 수정</h1>
				</div>
				<div class="board_content">
					<form method="post" action="${ contextPath }/board/update">
					<input type="hidden" name="bid" value="${ board.bid }">
						<div class="content">
							<h4>
								<span class="title_span">&nbsp;</span> 분류
							</h4>
							<span class="input_area"> 
							<select name="category">
									<option value="10" <c:if test="${ board.cid == 10 }">selected</c:if>>공통</option>
									<option value="20" <c:if test="${ board.cid == 20 }">selected</c:if>>운동</option>
									<option value="30" <c:if test="${ board.cid == 30 }">selected</c:if>>등산</option>
									<option value="40" <c:if test="${ board.cid == 40 }">selected</c:if>>게임</option>
									<option value="50" <c:if test="${ board.cid == 50 }">selected</c:if>>낚시</option>
									<option value="60" <c:if test="${ board.cid == 60 }">selected</c:if>>요리</option>
									<option value="70" <c:if test="${ board.cid == 70 }">selected</c:if>>기타</option>
							</select>
							</span>

							<h4>
								<span class="title_span">&nbsp;</span> 제목
							</h4>
							<span class="input_area"> <input type="text" name="title" value="${ board.btitle }"
								required>
							</span>

							<h4>
								<span class="title_span">&nbsp;</span> 내용
							</h4>
							<textarea class="textarea" rows="20" cols="100" name="content"
								required>${ board.bcontent }</textarea>
						</div>

						<div class="btn_area">
							<button type="button" onclick="location.href='${ contextPath }/board/list'">목록으로</button>
							<button type="submit">수정하기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>