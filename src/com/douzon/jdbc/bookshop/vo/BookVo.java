package com.douzon.jdbc.bookshop.vo;

public class BookVo //select의 결과값을 어떻게 담을지만 고려, 객체지향x
{
	private long no;
	private String title;
	private String status;
	private long authorNo;
	private String authorName;

	
	//화면 뿌리고 사용자입력에 맞게 vo를 만듬
	// db에 어떤값이 들어가냐에 맞춰서 셋팅
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", status=" + status + ", authorNo=" + authorNo
				+ ", authorName=" + authorName + "]";
	}
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public long getAuthorNo() {
		return authorNo;
	}
	public void setAuthorNo(long authorNo) {
		this.authorNo = authorNo;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
