package com.lhs.book.vo;

public class BookVO {
	
	private int bookNo;
	private String title;
	private String subject;
	private String content;
	private String studyDate;
	private String chapter;

	public BookVO() {
	}

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStudyDate() {
		return studyDate;
	}

	public void setStudyDate(String studyDate) {
		this.studyDate = studyDate;
	}

	public String getChapter() {
		return chapter;
	}

	public void setChapter(String chapter) {
		this.chapter = chapter;
	}

	@Override
	public String toString() {
		return "BookVO [bookNo=" + bookNo + ", title=" + title + ", subject=" + subject + ", content=" + content
				+ ", studyDate=" + studyDate + ", chapter=" + chapter + "]";
	}

}
