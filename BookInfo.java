package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInfo {

	public static void main(String[] args) {
		
		// 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. JDBC 드라이버 (Oracle) 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connection 얻어오기
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            conn = DriverManager.getConnection(url, "webdb", "1234");
            System.out.println("접속성공");

            // 3. SQL문 준비 / 바인딩 / 실행
            //String query = " select author_id, author_name name, author_desc from author"
             //            + " order by author_id " ;
            //book 테이블 검색
            //String query = " select book_id, title ,pubs, pub_date, author_id from book";
            //book + author 조인!
            String query ="SELECT book_id , title , pubs , pub_date , author_name \r\n"
            		+ "FROM BOOK b ,AUTHOR a "
            		+ "WHERE b.AUTHOR_ID = a.AUTHOR_ID ";
            pstmt = conn.prepareStatement(query);
            
            rs = pstmt.executeQuery();

            // 4.결과처리
            System.out.println("책 번호 " + "\t" + "책 제목" + "\t" + "책출판" + "\t" + "       일자" + "\t\t\t"  + "책저자" + "\t\n");
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String bookTitle = rs.getString("title");
                String bookPubs = rs.getString("pubs");
                String pubDate = rs.getString("pub_date");
                //String author_id = rs.getString("author_id");
                String authorname = rs.getString("author_name");                
                System.out.println(bookId + "\t" + bookTitle + "\t" + bookPubs + "\t" + pubDate + "\t"  + authorname + "\t");
            }

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
