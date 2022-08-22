package com.jdbc.jo1;

import java.util.List;
import java.util.Scanner;

public class FindBookInfo2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		BookDao dao = new BookDaoInpl();
		//리스트 형태라 리스트로 받는다.
//		List<BookVo> list =dao.getList();
//		
//		
//		//받은 값을 for문으로 출력한다. arraylist
//		for(BookVo vo : list) {
//			System.out.println(vo);
//		}
//		
		//검색
//		List<BookVo> list2 = dao.getSearchList("문");
//		
//		for(BookVo vo : list2) {
//			System.out.println(vo);
//		}

		
		//
		System.out.print("책 정보 검색을 위한 검색어를 입력하세요>>");
		String keyword = sc.nextLine();
		
		List<BookVo> list3 = dao.getSearchList(keyword);
		
		for(BookVo vo : list3) {
			System.out.println(vo);
		}
		

	}

}
