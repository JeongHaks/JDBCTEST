package com.jdbc.jo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
  

}
