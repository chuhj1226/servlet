<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시판</title>
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

ul, li {
   margin: 0;
   padding: 0;
}

.list_div {
   width : 100%;
}

.board_list {
   list-style : none;
   margin:50px 15px;
   display : grid;
   grid-template-columns: 230px 230px 230px;
   gap : 30px;
}

.board_list .box:hover {
   cursor : pointer;
   transform:scale(1.05);
}
.board_list .category {
   color : lightgray;
   font-size : 0.8rem;
}

.board_list .title{
   font-weight : bold;
   text-align:center;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
}

.board_list .writer {
   color : lightgray;
   text-align : center;
   font-size : 0.8rem;
}

.board_list img {
   max-width : 230px;
   height : 180px;
}

.btn_area {
   text-align:center;
}

.btn_area button {
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
         <div class="board_area">
            <div class="board_title">
               <h1>사진 게시판</h1>
            </div>
            <div class="list_div">
               <ul class="board_list">
               <c:forEach var="board" items="${ boardList }">
               <li>
               <div class="box">
                  <img src="${ contextPath }${ board.photoList.get(0).filePath }${ board.photoList.get(0).changeName }">
                  <p class="category">[ ${ board.cname } ]</p>
                  <p class="title">${ board.btitle }</p>
                  <p class="writer">${ board.userName } | 조회수 : ${ board.bcount }</p>
               </div>   
               </c:forEach>
               </ul>
            </div>

			<c:if test="${ !empty loginUser }">
			<div class="btn_area">
				<button onclick="location.href='${contextPath }/gallery/insert'">작성하기</button>
			</div>
			</c:if>
			
         </div>
      </div>
   </div>
   
</body>
</html>