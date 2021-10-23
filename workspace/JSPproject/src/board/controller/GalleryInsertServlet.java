package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import board.model.vo.Attachment;
import board.model.vo.Board;
import common.MyFileRenamePolicy;
import member.model.vo.Member;

/**
 * Servlet implementation class GalleryInsertServlet
 */
@WebServlet("/gallery/insert")
public class GalleryInsertServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GalleryInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("/WEB-INF/views/gallery/galleryInsertView.jsp").forward(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   /* 
	    * form 전송 시 enctype을 multipart/form-data로 전송하는 경우
	    * 기존 방식 request.getParameter("name")과 같은 방식으로 값을 추출할 수 없음*/
//      String btitle = request.getParameter("title");
//      String bcontent = request.getParameter("content");
//      System.out.println("btitle : " + btitle);
//      System.out.println("bcontent : " + bcontent);

	   
      /* enctype이 multipart/form-data로 전송되었는지 확인하고 아닐 경우 에러페이지 이동 */
      if(!ServletFileUpload.isMultipartContent(request)) {
         request.setAttribute("message", "잘못된 전송입니다.");
         request.getRequestDispatcher("/WEB-INF/views/common/errorpage.jsp").forward(request, response);
         return;
      }
      
      /* com.orelilly.servlet의 약자로 cos.jar 라이브러리 사용하여 파일 업로드 처리
       * http://www.servlets.com */
      
      /* 1. 전송 파일 용량 제한 : 10MB로 제한 */
      int maxSize = 1024*1024*10;
      
      /* 2. 웹 서버 컨테이너 경로 추출 후 파일이 실제 저장 될 경로 지정 */
      String root = request.getSession().getServletContext().getRealPath("/");
      String savePath = root + "resources\\uploadFiles\\";
      
      /* HttpServletRequest => MultipartRequest 변경 */
      MultipartRequest multiRequest 
      	= new MultipartRequest(request, savePath, maxSize, "UTF-8"
      			, /* new DefaultFileRenamePolicy()*/ new MyFileRenamePolicy());
      
      /* 위의 MultipartRequest 객체 생성과 동시에 업로드한 파일들이 서버의 지정 경로에 저장 됨
       * 즉, 이후의 프로세스에 문제가 있건 없건 우선서버에 저장 되므로 추후 프로세스에서 문제가 생길 경우
       * 업로드 된 파일을 삭제할 것
       * 
       * 사용자가 올린 파일명을 그대로 저장할 경우 같은 파일명이 있다면 덮어쓰기 처리 되므로
       * 파일 명을 수정하여 저장하는 것이 일반적임 
       * 
       * DefaultFileRenamePolicy는 cos.jar가 제공하는 클래스로
       * 같은 파일명이 있을 경우 파일명 뒤에 카운팅 된 숫자를 붙여줌
       * user.png -> user1.png -> user2.png
       * 
       * DefaultFileRenamePolicy -> MyFileRenamePolicy (변경)
       * */
      
      /*DB의 Board와 Attachment에 저장*/
      
      int cid = Integer.parseInt(multiRequest.getParameter("category"));
      String btitle = multiRequest.getParameter("title");
      String bcontent = multiRequest.getParameter("content");
      int bwriter = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
      
      Board board = new Board();
      board.setCid(cid);
      board.setBtitle(btitle);
      board.setBcontent(bcontent);
      board.setBwriter(bwriter);
      board.setBtype(2);			// 사진 게시글
      
      List<Attachment> photoList = new ArrayList<>();
      String[] fileNames = {"thumbnail", "contentImg1","contentImg2"};
     
      for(int i = 0 ; i < fileNames.length; i++) {
    	  /* contentImg는 optional이므로 파일 첨부 되지 않았을 수도 있음 
    	   * 해당 태그에 파일이 업로드 되지 않았을 경우 다음 fileName으로 넘어감 */
    	  if(multiRequest.getFilesystemName(fileNames[i]) == null)
    		  continue;
    	  
    	  Attachment attachment = new Attachment();
    	  attachment.setFilePath("/resources/uploadFiles/");
    	  /* getOriginalFileName() : 실제 사용자가 업로드 한 파일명 */
    	  attachment.setOriginName(multiRequest.getOriginalFileName(fileNames[i]));
    	  /* getFilesystemName() : MyRenamePolicy의 rename 메소드에서 작성한대로 rename 된 파일명 */
    	  attachment.setChangeName(multiRequest.getFilesystemName(fileNames[i]));
    	  /* thumbnail file_level => 0, contentImg file_level => 1 */
    	  if(i == 0)
    		  attachment.setFileLevel(0);
    	  else
    		  attachment.setFileLevel(1);
    	  
    	  /* 파일이 첨부 된 개수만큼 attachment 객체가 photoList에 담김 */
    	  photoList.add(attachment);
      }
      
      /* board에 만들어진 attachment 데이터 설정 */
      board.setPhotoList(photoList);
      
      System.out.println(board);
   }

}