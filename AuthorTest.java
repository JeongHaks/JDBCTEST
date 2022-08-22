package com.jdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorTest {

  public static void main(String[] args) {
    List<AuthorVo> list = new ArrayList<AuthorVo>();
    AuthorDao dao = new AuthorDao();    
    AuthorVo[] voo = new AuthorVo[10];
    Scanner sc = new Scanner(System.in);
    
    boolean run = true;
    
    list = dao.getList();
  while(run) {
	    System.out.print("번호를 입력해주세요 : ");
	    int number;
	    number = sc.nextInt();
	    
	    switch(number) {    
	    	case 1:
	    		for(AuthorVo vo : list) {
	    		      //System.out.println("Author id :" + vo.getAuthor_id());
	    		      System.out.println( vo.toString() );
	    		    }
	    		break;
	    	case 2: //추가 기능
	    		dao.InsertAuthor(null);
	    		break;
	    		
	    	case 3: //삭제 기능
	    		dao.DeleteAuthor(null);
	    		break;
	    	case 4: //검색 기능
	    		
	    		break;
	    }
	  }
	  
     
    
    
  }

}
