package com.jdbc.jo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorTest {

  public static void main(String[] args) {
    List<AuthorVo> list = new ArrayList<AuthorVo>();
    AuthorDao dao = new AuthorDao();
    Scanner sc = new Scanner(System.in);    
    
    boolean run = true;
    int number;
    list = dao.getList();
    while(run) {
    	System.out.println(" ");
    	System.out.println("----------------------------------------------");
    	System.out.println(" 1.조회 | 2.입력 | 3.수정 | 4.삭제 | 5.검색 | 6.종료");
    	System.out.println("----------------------------------------------");
    	System.out.println(" ");
        System.out.print(" 번호를 입력해주세요 : " );
        
        number = sc.nextInt();    
        
	    switch(number) {
	    	case 1: //조회기능
	    		for(AuthorVo vo : list) {	    	
	    		      System.out.println( vo.toString() );
	    		    }
	    		break;
	    	case 2://입력기능
	    		dao.InsertAuthor();
	    		break;
	    	case 3: //수정기능
	    		dao.UpdateAuthor();
	    		break;
	    	case 4: //삭제 기능
	    		dao.DeleteAuthor();
	    		break;
	    	case 5: //검색 기능
	    		dao.SearchAuthor();
	    		break;
	    	case 6://포로그램 종료
	    		System.out.println("프로그램 종료");
	    		run = false;
	    		sc.close();
	    		break;
	    	default ://그 외 값을 입력했을 시 
	    		System.out.println("숫자를 잘 못 입력했수다.");
	    		break;
	    	}	    
    	}
  	}
}
