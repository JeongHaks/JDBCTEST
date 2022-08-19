package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcTest {

	public static void main(String[] args) {
		//jdbc 할 때 반드시 필요한거!
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234"); //주소 아이디 비번
			// 3. SQL문 준비 / 바인딩 / 실행
			String sql = "SELECT a.AUTHOR_ID , a.AUTHOR_NAME ,a.AUTHOR_DESC FROM AUTHOR a"; //SQL준비
			pstmt = conn.prepareStatement(sql); //바인딩
			rs = pstmt.executeQuery(); //실행
			// 4.결과처리
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", " 
								 + rs.getString(2) + ", "
 								 +  rs.getString(3)
								 );
			}
		}catch(ClassNotFoundException e) {
			System.out.println("error.드라이브 로딩 실패- : " + e);
		}catch(SQLException e) {
			 System.out.println("errer : " + e);
		}finally {				
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(conn != null) {conn.close();}
			}catch(SQLException e) {
				System.out.println("errer : " + e);
			}
		}
		
		

	}

}
