package com.jdbc.jo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoInpl implements BookDao{
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
  
  //책 정보 가져오기.(조회)
  @Override
  public List<BookVo> getList() {
    List<BookVo> list = new ArrayList<BookVo>();
    conn = getConnection();
    String sql = " SELECT b.BOOK_ID ,\n"
               + "       b.TITLE ,\n"
               + "       b.PUBS ,\n"
               + "       TO_CHAR(b.PUB_DATE ,'YYYY-MM-DD') PUB_DATE,\n"
               + "       a.AUTHOR_NAME \n"
               + " FROM BOOK b , AUTHOR a \n"
               + " WHERE a.AUTHOR_ID = b.AUTHOR_ID";
    try {
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        long book_id       = rs.getInt(1);
        String title       = rs.getString(2);
        String pubs        = rs.getString(3);
        String pub_date    = rs.getString(4);
        String author_name = rs.getString(5);
        BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_name);
        list.add(vo);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
// 책 정보 검색하기.
  @Override
  public List<BookVo> getSearchList(String keyWord) {
    List<BookVo> list = new ArrayList<BookVo>();
    conn = getConnection();
    String sql = " SELECT b.BOOK_ID ,\n"
               + "       b.TITLE ,\n"
               + "       b.PUBS ,\n"
               + "       TO_CHAR(b.PUB_DATE ,'YYYY-MM-DD') PUB_DATE,\n"
               + "       a.AUTHOR_NAME \n"
               + " FROM BOOK b , AUTHOR a \n"
               + " WHERE a.AUTHOR_ID = b.AUTHOR_ID \n"
               + " AND b.TITLE || b.PUBS || a.AUTHOR_NAME LIKE ? ";
    try {
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, '%'+ keyWord + '%');
      rs = pstmt.executeQuery();
      
      while(rs.next()) {
        long book_id       = rs.getInt(1);
        String title       = rs.getString(2);
        String pubs        = rs.getString(3);
        String pub_date    = rs.getString(4);
        String author_name = rs.getString(5);
        BookVo vo = new BookVo(book_id, title, pubs, pub_date, author_name);
        list.add(vo);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }
  
  //책 정보 추가하기
  @Override
  public void getInsert(long bookid, String botitle, String bopubs, String bo_date, String auname) {
		  conn = getConnection();
		  try {
			  
				  
				  System.out.println("연결 완료");
				  
				  String query = "";	
				  
				  pstmt.setLong(1, bookid);
				  pstmt.setString(2, botitle);
				  pstmt.setString(3, bopubs);
				  pstmt.setString(4, bo_date);
				  pstmt.setString(5, auname);
				  
				  int count = pstmt.executeUpdate();
				  
				  //결과 처리 횟수 출력
				  System.out.println(count + " 건 처리 완료");
		  }catch(SQLException e) {
			  System.out.println("error" + e);
		  }finally {
			  try {
				  if(pstmt != null) {pstmt.close();}
				  if(conn != null) {conn.close();}
			  }catch(SQLException e) {
				  System.out.println("error" + e);
		      }
	  	}
  	}


}
