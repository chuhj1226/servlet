package board.model.vo;

import java.sql.Date;

public class Attachment {

   private int fid;            // 첨부파일 pk
   private int bid;            // 참조 게시글 아이디
   private String originName;      // 파일 업로드 시의 원본 파일명
   private String changeName;      // 서버 저장시의 변경 파일명
   private String filePath;      // 파일 저장 경로
   private Date uploadDate;      // 업로드된 시간
   private int fileLevel;         // 대표 사진0, 내용 사진1
   private int downloadCount;      // 다운로드 횟수
   private String status;         // 삭제 여부
   
   
   public Attachment() {}


   public Attachment(int fid, int bid, String originName, String changeName, String filePath, Date uploadDate,
         int fileLevel, int downloadCount, String status) {
      super();
      this.fid = fid;
      this.bid = bid;
      this.originName = originName;
      this.changeName = changeName;
      this.filePath = filePath;
      this.uploadDate = uploadDate;
      this.fileLevel = fileLevel;
      this.downloadCount = downloadCount;
      this.status = status;
   }


   public int getFid() {
      return fid;
   }


   public void setFid(int fid) {
      this.fid = fid;
   }


   public int getBid() {
      return bid;
   }


   public void setBid(int bid) {
      this.bid = bid;
   }


   public String getOriginName() {
      return originName;
   }


   public void setOriginName(String originName) {
      this.originName = originName;
   }


   public String getChangeName() {
      return changeName;
   }


   public void setChangeName(String changeName) {
      this.changeName = changeName;
   }


   public String getFilePath() {
      return filePath;
   }


   public void setFilePath(String filePath) {
      this.filePath = filePath;
   }


   public Date getUploadDate() {
      return uploadDate;
   }


   public void setUploadDate(Date uploadDate) {
      this.uploadDate = uploadDate;
   }


   public int getFileLevel() {
      return fileLevel;
   }


   public void setFileLevel(int fileLevel) {
      this.fileLevel = fileLevel;
   }


   public int getDownloadCount() {
      return downloadCount;
   }


   public void setDownloadCount(int downloadCount) {
      this.downloadCount = downloadCount;
   }


   public String getStatus() {
      return status;
   }


   public void setStatus(String status) {
      this.status = status;
   }


   @Override
   public String toString() {
      return "Attachment [fid=" + fid + ", bid=" + bid + ", originName=" + originName + ", changeName=" + changeName
            + ", filePath=" + filePath + ", fileLevel=" + fileLevel + ", downloadCount=" + downloadCount
            + ", status=" + status + "]";
   }
   
   
}