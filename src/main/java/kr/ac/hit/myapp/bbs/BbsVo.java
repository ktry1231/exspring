package kr.ac.hit.myapp.bbs;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BbsVo {
	private int bbsNo; 
	private String bbsTitle;
	private String bbsContent;
	private String bbsWriter;
	private Date bbsRegDate; 
	private int bbsCount;
	
	//데이터베이스에서 첨부파일 테이블과 조인하여 조회한 결과를 담기 위해서 
	private List<AttachVo> attList; //2개 이상의 첨부파일 정보
	
	public List<AttachVo> getAttList() {
		return attList;
	}
	public void setAttList(List<AttachVo> attList) {
		this.attList = attList;
	}
	
	//파일 데이터를 받기위한 변수를 MultipartFile 타입으로 선언
	//같은 파라미터 이름으로 전송되는 파일이 2개 이상인 경우, List<MultipartFile> 타입으로 선언
	private List<MultipartFile> uploadList;
	
	public List<MultipartFile> getUploadList() {
		return uploadList;
	}
	public void setUploadList(List<MultipartFile> uploadList) {
		this.uploadList = uploadList;
	}
	
	public int getBbsNo() {
		return bbsNo;
	}
	public void setBbsNo(int bbsNo) {
		this.bbsNo = bbsNo;
	}
	public String getBbsTitle() {
		return bbsTitle;
	}
	public void setBbsTitle(String bbsTitle) {
		this.bbsTitle = bbsTitle;
	}
	public String getBbsContent() {
		return bbsContent;
	}
	public void setBbsContent(String bbsContent) {
		this.bbsContent = bbsContent;
	}
	public String getBbsWriter() {
		return bbsWriter;
	}
	public void setBbsWriter(String bbsWriter) {
		this.bbsWriter = bbsWriter;
	}
	public Date getBbsRegDate() {
		return bbsRegDate;
	}
	public void setBbsRegDate(Date bbsRegDate) {
		this.bbsRegDate = bbsRegDate;
	}
	public int getBbsCount() {
		return bbsCount;
	}
	public void setBbsCount(int bbsCount) {
		this.bbsCount = bbsCount;
	}
}
