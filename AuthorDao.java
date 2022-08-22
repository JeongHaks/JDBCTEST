package com.jdbc;

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
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }
  
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
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return list;
  }
  //입력기능
  public int InsertAuthor(AuthorVo vo) {
	  int re = -1; //반환 실패 시 출력할 값
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
	return re;
	  
  }
  
  //삭제 기능
  public int DeleteAuthor(AuthorVo del) {

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
          //String query = "DELETE FROM book WHERE book_id = ?" ;
          
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
  
	  return re2;
  }
  
}
