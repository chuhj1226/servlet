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

ul, li {
   margin: 0;
   padding: 0;
}

.board_title {
   border-bottom: 1px solid #282A35;
}

.board_list {
   margin: 20px 30px;
   min-height: 565px;
}

.board_list>ul {
   border-bottom: 1px solid #f3f5f7;
   height: 50px;
   line-height: 50px;
   display: flex;
   justify-content: space-around;
   list-style: none;
}

.board_list>ul.last {
   border: 0;
}

.board_list>ul>li {
   text-align: center;
}

.board_header {
   background: #282A35;
   color: white;
   font-weight: bold;
}

.board_list .id {
   width: 60px;
}

.board_list .category {
   width: 60px;
}

.board_list .title {
   width: 350px;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
}

.board_list .writer {
   width: 80px;
}

.board_list .count {
   width: 60px;
}

.board_list .date {
   width: 100px;
}

.board_ul:hover {
   background: #f3f5f7;
   cursor: pointer;
}

.board_paging {
   height: 50px;
   line-height: 50px;
   display: flex;
   justify-content: center;
   list-style: none;
   width: 480px;
   margin: auto;
}

.board_paging a {
   text-decoration: none;
   color: #282A35;
   width: 40px;
   display: block;
   text-align: center;
}

.board_paging a.current_page {
   border-bottom: 2px solid #282A35;
   font-weight: bold;
}

.search_area {
   text-align: center;
   padding: 30px;
}

.search_area select {
   width: 150px;
   height: 30px;
   border: 0px;
}

.input_area {
   border: solid 1px #dadada;
   padding: 10px 10px 14px 10px;
   margin-right: 20px;
   background: white;
}

.input_area input {
   width: 250px;
   height: 30px;
   border: 0px;
}

.input_area input:focus, .search_area select:focus {
   outline: none;
}

.search_area button {
   width: 100px;
   height: 35px;
   border: 0px;
   color: white;
   background: #282A35;
   margin: 5px;
}
</style>
</head>
<body>
   <jsp:include page="/WEB-INF/views/common/menubar.jsp" />
   <div class="outer">
      <div class="wrap">
         <div class="board_area">
            <div class="board_title">
               <h1>게시판</h1>
            </div>
            <div class="board_list">
               <ul class="board_header">
                  <li class="id">글번호</li>
                  <li class="category">분류</li>
                  <li class="title">제목</li>
                  <li class="writer">작성자</li>
                  <li class="count">조회수</li>
                  <li class="date">작성일</li>
               </ul>
               <c:forEach var="board" items="${ boardList }">
               <ul class="board_ul" onclick="detailView(${board.bid})">
                  <li class="id">${ board.bid }</li>
                  <li class="category">${ board.cname }</li>
                  <li class="title">${ board.btitle }</li>
                  <li class="writer">${ board.userName }</li>
                  <li class="count">${ board.bcount }</li>
                  <li class="date">${ board.createDate }</li>
               </ul>
               </c:forEach>
            </div>
            
            <c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
            	<c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
            </c:if>
            
            <ul class="board_paging">
            <!-- 맨 처음으로 이동하는 버튼(<<) -->   
            <li><a href="${ contextPath }/board/list?page=1${searchParam}">&lt;&lt;</a></li>
            
            <!-- 이전 페이지로(<) -->
            <li>
            <c:choose>
               <c:when test="${ pi.page > 1 }">
               <a href="${ contextPath }/board/list?page=${ pi.page - 1 }${searchParam}">&lt;</a></c:when>
               <c:otherwise><a href="#">&lt;</a></c:otherwise>
            </c:choose>
            </li>
            
            <!-- 최대 10개의 페이지 목록 -->
            <c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
            <li>
            <c:choose>
            	<c:when test="${ p eq pi.page }">
            	<a href="#" class="current_page">${ p }</a>
            	</c:when>
            	<c:otherwise>
            	<a href="${ contextPath }/board/list?page=${ p }${searchParam}">${ p }</a>
            	</c:otherwise>
            </c:choose>
            </li>
            </c:forEach>
            
            <!-- 다음 페이지로(>) -->
            <li>
            <c:choose>
               <c:when test="${ pi.page < pi.maxPage }">
               <a href="${ contextPath }/board/list?page=${ pi.page + 1 }${searchParam}">&gt;</a></c:when>
               <c:otherwise><a href ="#">&gt;</a></c:otherwise>
            </c:choose>
            </li>
            <!-- 맨 끝으로 이동하는 버튼(>>) -->
               <li><a href="${ contextPath }/board/list?page=${ pi.maxPage }${searchParam}">&gt;&gt;</a></li>
            </ul>
         </div>
         <div class="search_area">
            <form method="get" action="${ contextPath }/board/list">
               <select id="searchCondition" name="searchCondition">
                  <option value="title" 
                  <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
                  <option value="content"
                  <c:if test="${ param.searchCondition == 'content' }">selected</c:if>>내용</option>
                  <option value="writer"
                  <c:if test="${ param.searchCondition == 'writer' }">selected</c:if>>작성자</option>
               </select> 
               <span class="input_area"> 
                  <input type="search" name="searchValue" value="${ param.searchValue }">
               </span>
               <button type="submit">검색하기</button>
               <c:if test="${ !empty loginUser }">
               <button type="button" onclick="location.href='${ contextPath }/board/insert'">작성하기</button>
         	   </c:if>
            </form>
         </div>
      </div>
   </div>
   
   <c:choose>
   	<c:when test="${ !empty loginUser }">
   		<script>
   			function detailView(bid){
   				location.href='${contextPath}/board/detail?bid='+bid;
   			}
   		</script>
   	</c:when>
   	<c:otherwise>
   		<script>
   			function detailView(bid){
   				alert('로그인 후 이용 가능합니다');
   				location.href='${contextPath}/login';
   			}
   		</script>
   	</c:otherwise>
   </c:choose>
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
</body>
</html>