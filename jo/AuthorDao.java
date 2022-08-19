package com.jdbc.jo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorDao {
	  private Connection conn = null;
	  private PreparedStatement pstmt = null;
	  private ResultSet rs = null;
  
	  public Connection getConnection() {
	    Connection conn = null;
	    
	    try {
		      Class.forName("oracle.jdbc.driver.OracleDriver");
		      String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		      conn = DriverManager.getConnection(dburl, "webdb", "1234");
	    } catch (ClassNotFoundException e) {	  e.printStackTrace();   } 
	      catch (SQLException e) {  	  e.printStackTrace();    }
	    return conn;
	    
	  }
	  
	  //조회기능
	  public List<AuthorVo> getList(){
		    List<AuthorVo> list = new ArrayList<AuthorVo>();
		    conn = getConnection();
		    String sql = " SELECT AUTHOR_ID, AUTHOR_NAME, AUTHOR_DESC FROM AUTHOR ";
	    
		    try {
			      pstmt = conn.prepareStatement(sql);
			      rs = pstmt.executeQuery();
	      
	            while(rs.next()) {
				        int author_id = rs.getInt(1);
				        String author_name = rs.getString(2);
				        String author_desc = rs.getString(3);
				        AuthorVo vo = new AuthorVo(author_id, author_name, author_desc);
				        list.add(vo);
	            }
		    } catch (SQLException e) {   e.printStackTrace();   }
	    return list;
	  }
	  
	  //입력 기능
	  public void InsertAuthor() {
		  int re =-1; //동작실패시 반환값	  
		  Scanner sc1 = new Scanner(System.in);
		  String name;
		  String desc;
		  
		  try {
	          // 1. JDBC 드라이버 (Oracle) 로딩
	          Class.forName("oracle.jdbc.driver.OracleDriver");
	          
	          conn = getConnection();
	          
	          System.out.println("접속성공");
	
	          // 3. SQL문 준비 / 바인딩 / 실행
	          //String query = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )"; //값을 받을 수 있도록 ? 함
	          String query = "INSERT INTO author VALUES (seq_book_id.nextval, ?,?)"; //값을 받을 수 있도록 ? 함
	          pstmt = conn.prepareStatement(query);
	          System.out.print("name을 입력해주세요 : ");
	          name = sc1.nextLine();
	          System.out.print("desc을 입력해주세요 : ");
	          desc = sc1.nextLine();
	          //pstmt.setString(1, "성은정");     
	          
	          pstmt.setString(1, name);
	          pstmt.setString(2, desc);          
	
	          int count = pstmt.executeUpdate();
	
	          // 4.결과처리
	          System.out.println(count + "건 처리");
	
	      } catch (ClassNotFoundException e) {
	          System.out.println("error: 드라이버 로딩 실패 - " + e);
	      } catch (SQLException e) {
	          System.out.println("error:" + e);
	      } finally {
	          // 5. 자원정리
	          try {
	              if (pstmt != null) {  pstmt.close();    }
	              if (conn != null) {   conn.close();    }
	              
	          } catch (SQLException e) {
	              System.out.println("error:" + e);
	          }
	
	      }
	  }
	  //수정기능
	  public void UpdateAuthor() {
		  int re1= -1; //실패시 반환할 숫자	  
		  Scanner sc2 = new Scanner(System.in);
		  String name1; //수정할 이름
		  String desc1; // 수정할 desc
		  int number; //수정할 번호
				  try {
			            // 1. JDBC 드라이버 (Oracle) 로딩
			            Class.forName("oracle.jdbc.driver.OracleDriver");
			            
			            conn = getConnection();
			            
			            System.out.println("접속성공");
	
			            String query = "UPDATE author " + 
			            			   "SET author_name =   ?, " +    
			            			   "    author_desc =   ? " + 		            			            			      
			            			   "WHERE author_id =   ?  ";
			            
			            
			            pstmt = conn.prepareStatement(query);
			            
			            //입력값 입력
			            System.out.print("name을 입력해주세요 : ");
			            name1 = sc2.nextLine();
			            System.out.print("desc을 입력해주세요 : ");
			            desc1 = sc2.nextLine();           
			            System.out.print("수정할 번호를 입력해주십쇼 : ");
			            number = sc2.nextInt();
			            pstmt.setString(1, name1);
			            pstmt.setString(2, desc1);		                       
			            pstmt.setInt(3, number);
	
			            int count = pstmt.executeUpdate();
	
			            // 4.결과처리
			            System.out.println(count + "건 Update 완료");
	
			        } catch (ClassNotFoundException e) {
			            System.out.println("error: 드라이버 로딩 실패 - " + e);
			        } catch (SQLException e) {
			            System.out.println("error:" + e);
			        } finally {
			            // 5. 자원정리
			            try {
			                if (pstmt != null) {  pstmt.close();  }
			                if (conn != null) {   conn.close();   }
			            } catch (SQLException e) {
			                System.out.println("error:" + e);
			            }
			        }	  
	  			}
			  //삭제기능
			  public void DeleteAuthor() {
				  int re2 = -1;
				  Scanner sc3 = new Scanner(System.in);
				  int number2; //삭제할 번호를 입력
			      try {
			          // 1. JDBC 드라이버 (Oracle) 로딩
			          Class.forName("oracle.jdbc.driver.OracleDriver");
			
			          // 2. Connection 얻어오기
			          conn = getConnection();
			          
			          System.out.println("접속성공");
			
			          // 3. SQL문 준비 / 바인딩 / 실행
			         String query = "DELETE FROM author WHERE author_id = ?" ;			        
			          
			          pstmt = conn.prepareStatement(query);
			          System.out.print("삭제할 번호를 입력해주십쇼 : ");
			          number2 = sc3.nextInt();
			          pstmt.setInt(1, number2);
			          
			          int count = pstmt.executeUpdate();
			
			          // 4.결과처리
			          System.out.println(count + "건 DELETE 완료");
			
			      } catch (ClassNotFoundException e) {
			          System.out.println("error: 드라이버 로딩 실패 - " + e);
			      } catch (SQLException e) {
			          System.out.println("error:" + e);
			      } finally {
			          // 5. 자원정리
			          try {
			              if (pstmt != null) {pstmt.close(); }
			              if (conn != null) {conn.close(); }
			          } catch (SQLException e) {
			              System.out.println("error:" + e);
			          }
			      }
			  }
	  
			  //검색 기능
				  public void SearchAuthor() { 
					  int re3 = -1;
					  Scanner sc = new Scanner(System.in);
					  try {
				            // 1. JDBC 드라이버 (Oracle) 로딩
				            Class.forName("oracle.jdbc.driver.OracleDriver");
			
				            // 2. Connection 얻어오기
				            String url = "jdbc:oracle:thin:@localhost:1521:xe";
				            conn = DriverManager.getConnection(url, "webdb", "1234");
				            System.out.println("접속성공");
			
				            //쿼리문 작성
				            String query ="SELECT author_id ,"
				            		+ "author_name ,"
				            		+ "author_desc "	            		
				            		+ "FROM AUTHOR "	            		
				            		+ "WHERE author_name = ";
				         
				           
				            System.out.print("저자 이름를 입력해주세요 : ");
				            String authornamed = sc.nextLine(); 
				            
				            pstmt = conn.prepareStatement(query +"'"+authornamed +"'");
				            
				            rs = pstmt.executeQuery();
			
				            // 4.결과처리	            
				            
				            while (rs.next()) {
				                int authorid = rs.getInt("author_id");
				                String authorname = rs.getString("author_name");
				                String authordesc = rs.getString("author_desc");	                	                
				                
				                System.out.println(authorid + "\t" + authorname + "\t" + authordesc + "\t");
				            }            
				        } catch (ClassNotFoundException e) {
				            System.out.println("error: 드라이버 로딩 실패 - " + e);
				        } catch (SQLException e) {
				            System.out.println("error:" + e);
				        } finally {
				            // 5. 자원정리
				            try {
				            	if (rs != null) { rs.close(); }
				                if (pstmt != null) { pstmt.close(); }
				                if (conn != null) {  conn.close();  }				                
				            } catch (SQLException e) {
				                System.out.println("error:" + e);
				            }
			
		          }		  
	  }
}