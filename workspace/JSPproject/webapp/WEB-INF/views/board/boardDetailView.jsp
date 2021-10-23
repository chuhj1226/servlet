<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

.board_content .subject {
   line-height : 35px;
   display: grid;
   grid-template-columns: 100px 100px 300px 240px;
   border-bottom: 1px solid #f3f5f7;
   text-align:right;
}

.board_content .subject span:nth-child(3) {
   grid-area: 1/4/1/5;
}

.board_content .subject span:nth-child(4) {
   grid-area: 2/4/2/5;
}

.board_content .subject span:nth-child(5) {
   grid-area: 3/4/3/5;
}


.board_content .content {
   height: 500px;
   overflow: auto;
   margin-bottom: 30px;
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
   cursor : pointer;
}

.reply_write {
   display:flex;
   justify-content: center;
   align-items: center;
   padding : 20px;
}

.reply_content {
   width:500px;
   height : 50px;
   resize : none;
}

.reply_ul {
   display : flex;
   justify-content: center;
   list-style: none;
   padding : 5px;
}

.reply_ul .rwriter {
   width : 150px;
}

.reply_ul .rcontent {
   width : 400px;
}

.reply_ul .rdate {
   width : 150px;
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
               <h1>게시판</h1>
            </div>
            <div class="board_content">
               <div class="subject">
                  <span> 글번호 : ${ board.bid }</span> 
                  <span> 조회수 : ${ board.bcount }</span>
                  <span> 작성자 : ${ board.userName }</span> 
                  <span> 작성일 : <fmt:formatDate value="${ board.createDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss" /></span> 
                  <span> 수정일 : <fmt:formatDate value="${ board.modifyDate }" type="both" pattern="yyyy.MM.dd HH:mm:ss" /></span> 
               </div>
               <div>
                  
                  <h4>
                     <span class="title_span">&nbsp;</span> 분류
                  </h4>
                  <p>${ board.cname }</p>
                  
                  <h4>
                     <span class="title_span">&nbsp;</span> 제목
                  </h4>
                  <p>${ board.btitle }</p>

                  <h4>
                     <span class="title_span">&nbsp;</span> 내용
                  </h4>
                  <pre class="content">${ board.bcontent }</pre>
               </div>
               
               <div class="reply_area">
               <h4>
                  <span class="title_span">&nbsp;</span> 댓글
               </h4>
                  <div class="reply_write">
                     <textarea class="reply_content"></textarea>
                     <button>댓글등록</button>
                  </div>
               
                  <div class="reply_list">
                                       
                  </div>
               </div>
               <div class="btn_area">
                  <button type="button" onclick="location.href='${contextPath}/board/list'">목록으로</button>
                  <c:if test="${ loginUser.userNo == board.bwriter }">
                     <button type="button" onclick="updateBoardView()">수정하기</button>
                     <button type="button" onclick="deleteBoard()">삭제하기</button>
                  </c:if>
               </div>
            </div>
         </div>
      </div>
   </div>
    <c:if test="${ loginUser.userNo == board.bwriter }">
      <form name="boardForm" method="post">
         <input type="hidden" name="bid" value="${ board.bid }">
      </form>         
      <script>      
         function updateBoardView(){
            document.forms.boardForm.action = "${contextPath}/board/updateView";
            document.forms.boardForm.submit();
         }
         
         function deleteBoard(){
            if(confirm("이 게시글을 삭제하시겠습니까?")){
               document.forms.boardForm.action = "${contextPath}/board/delete";
               document.forms.boardForm.submit();
            }
         }
      </script>      
      </c:if>
</body>
</html>