<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
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
         <form id="joinForm" action="<%= request.getContextPath() %>/memberModify"
         method="post" onsubmit="return validate();">
            <h1>정보 수정</h1>
            
            <h4>* 아이디</h4>
            <span class="input_area"><input type="text" maxlength="13" name="userId" 
            value="<%= loginUser.getUserId() %>" readonly></span>
            
            <h4>* 비밀번호</h4>
            <span class="input_area"><input type="password" maxlength="15" name="userPwd" readonly></span>
            <button id="pwdUpdateBtn" type="button" 
            onclick = "openPopup('<%= request.getContextPath() %>/pwdModify', 'pwdModify', 500, 500);">비밀번호 변경</button>
            <!-- 500은 창의 크기 -->
            <!-- type을 넣지 않으면 default로 submit이 됨 -->
            
            <h4>* 이름</h4>
            <span class="input_area"><input type="text" maxlength="5" name="userName" 
            value="<%= loginUser.getUserName() %>"  required></span>
            
            <%-- 
               필수 정보가 아닌 값들은 값이 비어있을 경우 null로 리턴 되므로 ""로 처리할 수 있도록 함
             --%>
            <h4>연락처</h4>
            <span class="input_area"><input type="tel" maxlength="11" name="phone" 
            value="<%= loginUser.getPhone() != null ? loginUser.getPhone() : "" %>" placeholder="(-없이)01012345678"></span>
                              
            <h4>이메일</h4>
            <span class="input_area"><input type="email" name="email"
            value="<%= loginUser.getEmail() != null ? loginUser.getEmail() : "" %>"></span>
            
            <%--
               주소의 경우 address 안에 | 기준으로 문자열을 합쳐서 저장되어 있으므로 다시 | 기준으로 배열로 만들기
             --%>
             
             <%
                String[] address;
                if(loginUser.getAddress() != null){
                   address = loginUser.getAddress().split("\\|");
                   // split : 정규표현식에서 or 표현하는 문자. 메타문자로 기능하기 위해서는 \\| or [|] 이렇게 작성 해야 함
                } else {
                   address = new String[] {"", "", ""};
                }
             %>
            <h4>우편번호</h4>
            <span class="input_area"><input type="text" name="address" class="postcodify_postcode5" 
            value="<%=address[0] %>" readonly></span> <!-- 직접 입력 막고 api 값만 입력될 수 있게 -->
            <button type="button" id="postcodify_search_button">검색</button>
            <h4>도로명주소</h4>
            <span class="input_area"><input type="text" name="address" class="postcodify_address" 
            value="<%=address[1] %>" readonly></span>
            <h4>상세주소</h4>
            <span class="input_area"><input type="text" name="address" class="postcodify_details"
            value="<%=address[2] %>"></span>
            
            <%--
               체크 된 checkbox에 해당하는 인덱스에checked 문자열 담기
             --%>
             <%
                String[] checkedInterest = {"", "", "", "", "", ""};
                
                if(loginUser.getInterest() != null){
                   // 회원이 체크한 값 배열에 담기
                   String[] interest = loginUser.getInterest().split("[|]");
                   for(int i = 0; i < interest.length; i++){
                      switch(interest[i]){
                      case "운동" : checkedInterest[0] = "checked"; break;
                      case "등산" : checkedInterest[1] = "checked"; break;
                      case "낚시" : checkedInterest[2] = "checked"; break;
                      case "요리" : checkedInterest[3] = "checked"; break;
                      case "게임" : checkedInterest[4] = "checked"; break;
                      case "기타" : checkedInterest[5] = "checked"; break;
                      }
                   }
                   
                }
             %>
            
            <h4>관심분야</h4>
            <span class="input_area">
               <input type="checkbox" id="sports" name="interest" value="운동" <%= checkedInterest[0] %>>
               <label for="sports">운동</label>
               <input type="checkbox" id="climbing" name="interest" value="등산" <%= checkedInterest[1] %>>
               <label for="climbing">등산</label>
               <input type="checkbox" id="fishing" name="interest" value="낚시" <%= checkedInterest[2] %>>
               <label for="fishing">낚시</label>
               <input type="checkbox" id="cooking" name="interest" value="요리" <%= checkedInterest[3] %>>
               <label for="cooking">요리</label>
               <input type="checkbox" id="game" name="interest" value="게임" <%= checkedInterest[4] %>>
               <label for="game">게임</label>
               <input type="checkbox" id="etc" name="interest" value="기타" <%= checkedInterest[5] %>>
               <label for="etc">기타</label>
            </span>
            <div class="btnArea">
               <button id="updateBtn">수정하기</button>
               <button id="deleteBtn" type="button"
               onclick="confirmAccountDelete();">탈퇴하기</button>
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
      // 원래 자바스크립트에서 해야하는게 맞으므로.. 여기서는 넘긴다라고 하심
      function validate(){
         // 알맞은 값 입력 시 넘어가게 js로 구현
         return true;
      }
      
      // 비밀번호 변경 팝업창 호출
      function openPopup(url, title, width, height){
         
         var left = (document.body.clientWidth/2) - (width/2);
         left += window.screenLeft; //듀얼모니터
         var top = (screen.availHeight/2) - (height/2);
         
         var options = "width=" + width + ",height=" + height + ",left="+left+",top="+top;
         window.open(url, title, options);
         
      }
      
      function confirmAccountDelete(){
    	  if(confirm("정말로 탈퇴하시겠습니까?"))
    		  location.href='<%=request.getContextPath()%>/accountDelete';
      }
    
   </script>
</body>
</html>








