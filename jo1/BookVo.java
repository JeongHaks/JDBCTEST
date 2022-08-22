package com.jdbc.jo1;

public class BookVo {
	
	//변수 선언
	private long book_id; //책 번호
	private String title; //책 제목
	private String pubs; // 책 출판
	private String pub_date; //출판 일자
	private String author_name; //작가 이름
	
	
	public long getBook_id() {
		return book_id;
	}
	public void setBook_id(long book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	/**
	 * @param book_id
	 * @param title
	 * @param pubs
	 * @param pub_date
	 * @param author_name
	 */
	
	public BookVo(long book_id, String title, String pubs, String pub_date, String author_name) {
		this.book_id = book_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_name = author_name;
	}

	@Override
	public String toString() {
		return "BookVo [book_id=" + book_id + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date
				+ ", author_name=" + author_name + "]";
	}
	
	
	
	
	
	
}
