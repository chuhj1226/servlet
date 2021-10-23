<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
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

.image_area {
   padding: 20px;
}

.image_area img {
   width: 100%;
   margin:20px;
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
               <h1>게시글 작성</h1>
            </div>
            <div class="board_content">
               <!-- 파일 업로드를 위해서는 반 드 시 form의 인코딩 속성을 변경해줘야 함(enctype 지정하기)
                   application/x-www-form-urlencoded
                   : 기본값, 모든 문자들은 서버로 보내지기 전에 인코딩됨을 명시
                   multipart/form-data
                   : 모든 문자들을 인코딩하지 않음을 명시
                   이 방식은 form 요소가 파일이나 이미지를 서버로 전송할 때 사용
                   text/plain
                   : 공백 문자는 "+" 기호로 변환하지만, 나머지 문자는 모두 인코딩되지 않음을 명시
                -->
               <form method="post" action="${ contextPath }/gallery/insert"
               enctype="multipart/form-data">
                  <div class="content">
                     <h4>
                        <span class="title_span">&nbsp;</span> 분류
                     </h4>
                     <span class="input_area"> <select name="category">
                           <option value="10">공통</option>
                           <option value="20">운동</option>
                           <option value="30">등산</option>
                           <option value="40">게임</option>
                           <option value="50">낚시</option>
                           <option value="60">요리</option>
                           <option value="70">기타</option>
                     </select>
                     </span>
                     <h4>
                        <span class="title_span">&nbsp;</span> 제목
                     </h4>
                     <span class="input_area"> <input type="text" name="title"
                        required>
                     </span>

                     <h4>
                        <span class="title_span">&nbsp;</span> 내용
                     </h4>
                     <textarea class="textarea" rows="20" cols="100" name="content"
                        required></textarea>

                     <h4>
                        <span class="title_span">&nbsp;</span> 대표 이미지 첨부
                     </h4>

                     <div class="image_area"></div>

                     <input type="file" name="thumbnail"
                     accept="image/gif,image/jpeg,image/png" required>

                     <h4>
                        <span class="title_span">&nbsp;</span> 추가 이미지 첨부(최대 2개)
                     </h4>

                     <div class="image_area"></div>
                     <div class="image_area"></div>

                     <input type="file" name="contentImg1"
                     accept="image/gif,image/jpeg,image/png">
                     <input type="file" name="contentImg2"
                     accept="image/gif,image/jpeg,image/png">


                  </div>
                  <div class="btn_area">
                     <button type="button">목록으로</button>
                     <button type="submit">작성하기</button>
                  </div>
               </form>
            </div>
         </div>
      </div>
   </div>
   
   <script src="${ contextPath }/resources/js/imagePreview.js"></script>


</body>
</html>