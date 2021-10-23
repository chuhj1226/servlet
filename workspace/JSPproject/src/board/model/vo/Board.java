package board.model.vo;

import java.util.Date;
import java.util.List;

public class Board {
   private int bid;         // 게시글 고유 번호
   private int btype;         // 게시글 타입(1. 일반게시판, 2. 사진 게시판)
   private int cid;         // 게시글 카테고리 id
   private String cname;      // 게시글 카테고리명(category 테이블 조인 결과 값 : id와 명칭을 같이 조인해서 가져옴)
   private String btitle;      // 게시글 제목
   private String bcontent;   // 게시글 내용
   private int bwriter;      // 게시글 작성자(user_no 참조 값)
   private String userName;   // 게시글 작성자명(Member 테이블 조인 결과 값)
   private int bcount;         // 게시글 조회수
   private Date createDate;   // 게시글 작성일 : sql Date 연월일 포맷되어서 2021-10-19 시분초 데이터 날라감
   private Date modifyDate;   // 게시글 수정일 : util Date를 사용해야 시분초 안날라감 대신 포맷팅이 안되어서 옴
   private String status;      // 게시글 상태(Y, N)
   
   
   private List<Attachment> photoList;      // 사진 게시판 첨부 파일 (게시글 하나에 여러 첨부 파일을 가질 수 있음)
   /*BID   NUMBER
   BTYPE   NUMBER
   CID   NUMBER
   BTITLE   VARCHAR2(100 BYTE)
   BCONTENT   VARCHAR2(4000 BYTE)
   BWRITER   NUMBER
   BCOUNT   NUMBER
   CREATE_DATE   DATE
   MODIFY_DATE   DATE
   STATUS   VARCHAR2(1 BYTE)*/
   
   
   public Board() {}
   
   public Board(int bid, int btype, int cid, String cname, String btitle, String bcontent, int bwriter,
         String userName, int bcount, Date createDate, Date modifyDate, String status) {
      super();
      this.bid = bid;
      this.btype = btype;
      this.cid = cid;
      this.cname = cname;
      this.btitle = btitle;
      this.bcontent = bcontent;
      this.bwriter = bwriter;
      this.userName = userName;
      this.bcount = bcount;
      this.createDate = createDate;
      this.modifyDate = modifyDate;
      this.status = status;
   }
   
   public Board(int bid, int btype, int cid, String cname, String btitle, String bcontent, int bwriter,
         String userName, int bcount, Date createDate, Date modifyDate, String status, List<Attachment> photoList) {
      super();
      this.bid = bid;
      this.btype = btype;
      this.cid = cid;
      this.cname = cname;
      this.btitle = btitle;
      this.bcontent = bcontent;
      this.bwriter = bwriter;
      this.userName = userName;
      this.bcount = bcount;
      this.createDate = createDate;
      this.modifyDate = modifyDate;
      this.status = status;
      this.photoList = photoList;
   }
   
   public int getBid() {
      return bid;
   }
   
   public void setBid(int bid) {
      this.bid = bid;
   }
   
   public int getBtype() {
      return btype;
   }
   
   public void setBtype(int btype) {
      this.btype = btype;
   }
   
   public int getCid() {
      return cid;
   }
   
   public void setCid(int cid) {
      this.cid = cid;
   }
   
   public String getCname() {
      return cname;
   }
   
   public void setCname(String cname) {
      this.cname = cname;
   }
   
   public String getBtitle() {
      return btitle;
   }
   
   public void setBtitle(String btitle) {
      this.btitle = btitle;
   }
   
   public String getBcontent() {
      return bcontent;
   }
   
   public void setBcontent(String bcontent) {
      this.bcontent = bcontent;
   }
   
   public int getBwriter() {
      return bwriter;
   }
   
   public void setBwriter(int bwriter) {
      this.bwriter = bwriter;
   }
   
   public String getUserName() {
      return userName;
   }
   
   public void setUserName(String userName) {
      this.userName = userName;
   }
   
   public int getBcount() {
      return bcount;
   }
   
   public void setBcount(int bcount) {
      this.bcount = bcount;
   }
   
   public Date getCreateDate() {
      return createDate;
   }
   
   public void setCreateDate(Date createDate) {
      this.createDate = createDate;
   }
   
   public Date getModifyDate() {
      return modifyDate;
   }
   
   public void setModifyDate(Date modifyDate) {
      this.modifyDate = modifyDate;
   }
   
   public String getStatus() {
      return status;
   }
   
   public void setStatus(String status) {
      this.status = status;
   }
   
   public List<Attachment> getPhotoList() {
      return photoList;
   }
   
   public void setPhotoList(List<Attachment> photoList) {
      this.photoList = photoList;
   }
   
   @Override
   public String toString() {
      return "Board [bid=" + bid + ", btype=" + btype + ", cid=" + cid + ", cname=" + cname + ", btitle=" + btitle
            + ", bcontent=" + bcontent + ", bwriter=" + bwriter + ", userName=" + userName + ", bcount=" + bcount
            + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + ", photoList="
            + photoList + "]";
   }

   

}