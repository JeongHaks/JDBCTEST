package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorInsertTest {

    public static void main(String[] args) {
        // 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connection 얻어오기
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "webdb", "1234");
            System.out.println("접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            //String query = "INSERT INTO author VALUES (seq_author_id.nextval, ?, ? )"; //값을 받을 수 있도록 ? 함
            String query = "INSERT INTO book VALUES (seq_book_id.nextval, ?, ?, ?,?)"; //값을 받을 수 있도록 ? 함
            pstmt = conn.prepareStatement(query);

            //pstmt.setString(1, "성은정");            
            pstmt.setString(1, "복싱왕");
            pstmt.setString(2, "타이슨");
            pstmt.setString(3, "1995-05-15");
            pstmt.setString(4, "1");

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
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }

        }

    }

}
