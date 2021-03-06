<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSPProject</title>
<style>
	#imageArea {
		width : 800px;
		margin : 100px auto;
		display : flex;
		justify-content : center;
		align-items : center;
		height : 70vh;
	
	}
</style>
</head>
<body>
	
	<!-- 
		* 회원
		- 로그인/로그아웃
		- 회원 가입/회원 정보 수정/회원 탈퇴
		
		* 공지사항/게시판
		- 게시글 조회
		- 게시글 작성/게시글 수정/게시글 삭제
		- 페이징/검색(필터)
	
		* 사진 게시판
		- 파일 업로드/다운로드
		
		* AJAX(비동기 통신)
		- 아이디 중복 여부 확인
		- 댓글
	 -->

   <!-- 모든 페이지에 include할 menubar.jsp 생성 -->
   <%@ include file="/WEB-INF/views/common/menubar.jsp" %>
   <!-- web-inf안에 넣는지 안 넣는지 차이는 사용자가 접근 가능하냐 못하냐의 차이 -->
   
   <div id="imageArea">
      <img id="servletJSP" src="<%= request.getContextPath() %>/resources/images/servlet-jsp.png">
   </div>
</body>
</html>