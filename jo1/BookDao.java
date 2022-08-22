package com.jdbc.jo1;

import java.util.List;

public interface BookDao {
	
	//책 정보 모두 가져오기
	public List<BookVo> getList();
	
	
	//책 정보 검색하며 가져오기.
	public List<BookVo> getSearchList(String keyWord);
	
	//책 정보 추가하기.
	public void getInsert(long boid, String botitle, String bopubs, String bodate, String auname);
}
