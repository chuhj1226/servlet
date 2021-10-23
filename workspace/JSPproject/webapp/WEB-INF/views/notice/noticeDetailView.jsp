<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
.outer {
	width: 800px;
	margin: auto;
}

.wrap {
	width: 780px;
	margin: 100px auto;
}

.notice_title {
	border-bottom: 1px solid #282A35;
}

.notice_content {
	padding: 0px 20px;
}

.notice_content .subject {
	height: 55px;
	line-height: 55px;
	display: flex;
	justify-content: space-between;
	border-bottom: 1px solid #f3f5f7;
}

.notice_content .content {
	height: 500px;
	overflow: auto;
	margin-bottom: 30px;
}

.title_span {
	background-color: #282A35;
}

.notice_area button {
	width: 100px;
	height: 35px;
	border: 0px;
	color: white;
	background: #282A35;
	margin: 5px;
	cursor : pointer;
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
			<div class="notice_area">
				<div class="notice_title">
					<h1>공지사항</h1>
				</div>
				<div class="notice_content">
					<div class="subject">
						<span> 글번호 : ${notice.nno }</span> 
						<span> 작성일 : ${notice.ndate } </span>
					</div>
					<div>
						<h4>
							<span class="title_span">&nbsp;</span> 제목
						</h4>
						<p>
							${ notice.ntitle }
						</p>

						<h4>
							<span class="title_span">&nbsp;</span> 내용
						</h4>
						<pre class="content">${ notice.ncontent }</pre>
					</div>
					<div class="btn_area">
						<button type="button"
						onclick="location.href='${contextPath}/notice/list'">목록으로</button>
						<c:if test="${ !empty loginUser && loginUser.userId == 'admin' }">
						<button type="button" onclick="updateNoticeView()">수정하기</button>
						<button type="button" onclick="deleteNotice()">삭제하기</button>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
		<c:if test="${ !empty loginUser && loginUser.userId == 'admin' }">
		<form name="noticeForm" method="post">
			<input type="hidden" name="nno" value="${ notice.nno }">
		</form>
		<script>
			function updateNoticeView(){
				document.forms.noticeForm.action = "${contextPath}/notice/updateView";
				document.forms.noticeForm.submit();
			}	
			
			function deleteNotice(){
				if(confirm("이 게시글을 삭제하시겠습니까?")){
				document.forms.noticeForm.action = "${contextPath}/notice/delete";
				document.forms.noticeForm.submit();
				}
			}
		</script>
		</c:if>
</body>
</html>