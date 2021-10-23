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

ul, li {
   margin: 0;
   padding: 0;
}

.notice_title {
   border-bottom: 1px solid #282A35;
}

.notice_list {
   margin: 20px 30px;
   min-height: 540px;
}

.notice_list>ul {
   border-bottom: 1px solid #f3f5f7;
   height: 50px;
   line-height: 50px;
   display: flex;
   justify-content: space-around;
   list-style: none;
}

.notice_list>ul.last {
   border: 0;
}

.notice_list>ul>li {
   text-align: center;
}

.notice_list .no {
   width: 100px;
}

.notice_list .title {
   width: 520px;
   text-align: left;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
}

.notice_list .date {
   width: 100px;
}

.notice_ul:hover {
   background: #f3f5f7;
   cursor: pointer;
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
   cursor : pointer;
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
            <div class="notice_list">
            <c:forEach var="notice" items="${ noticeList }">
               <ul class="notice_ul" onclick="detailView(${notice.nno})">
                  <li class="no">${ notice.nno }</li>
                  <li class="title">${ notice.ntitle }</li>
                  <li class="date">${ notice.ndate }</li>
               </ul>
            </c:forEach>
            </div>
         </div>
         <div class="search_area">
            <form method="get" action="${ contextPath }/notice/list">
            <select id="searchCondition" name="searchCondition">
                  <option value="title"
                  <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
                  <option value="content"
                  <c:if test="${ param.searchCondition == 'content' }">selected</c:if>>내용</option>
               </select>
               <span class="input_area">          
               <input type="search" name="searchValue"  value="${ param.searchValue }" required>
               </span>
               <button type="submit">검색하기</button>
               <c:if test="${ !empty loginUser && loginUser.userId == 'admin' }">
               <!-- 첫 번째 조건에서 만족 못하면 false -->
               <button id="noticeInsert" type="button"
               onclick="location.href='${ contextPath }/notice/insert'">작성하기</button>            
               </c:if>
            </form>
         </div>
      </div>
   </div>
   <script>
   		function detailView(nno) {
   			location.href='${contextPath}/notice/detail?nno='+nno;
   		}
   </script>
</body>
</html>