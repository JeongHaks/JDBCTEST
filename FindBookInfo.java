package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FindBookInfo {

	public static void main(String[] args) {

		// 0. import java.sql.*;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        Scanner sc = new Scanner(System.in);

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
           //검색 하기.
            String query ="SELECT book_id ,"
            		+ "title ,"
            		+ "pubs ,"
            		+ "pub_date ,"
            		+ "AUTHOR_NAME \r\n"
            		+ "FROM BOOK b ,AUTHOR a "
            		+ "WHERE b.AUTHOR_ID = a.AUTHOR_ID "
            		+ "AND  a.AUTHOR_NAME = ";
            //AND a.author_name like ? "
           
            System.out.print("책 저자를 입력해주세요 : ");
            String authornamed = sc.nextLine(); 
            
            pstmt = conn.prepareStatement(query +"'"+authornamed +"'");
// 		    방법 2)
//            pstmt.setString(5 +"%"+authornamed +"%");     //" ' " 이렇게 알아서 해준다. ""
            // pstmt.setString(5 ,authornamed);
            
            rs = pstmt.executeQuery();

            // 4.결과처리
            System.out.println("책 번호 " + "\t" + "책 제목" + "\t" + "책출판" + "\t" + "       일자" + "\t\t\t"  + "책저자" + "\t");
            
            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String bookTitle = rs.getString("title");
                String bookPubs = rs.getString("pubs");
                String pubDate = rs.getString("pub_date");
                String author_id = rs.getString("AUTHOR_NAME");
                
                System.out.println(bookId + "\t" + bookTitle + "\t" + bookPubs + "\t" + pubDate + "\t"+ author_id + "\t");
            }            
            
            //rs.getString(1), rs.getString(2) ... 이런식으로 값을 불러오는 방법
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
                if (sc != null) {  sc.close();  }
            } catch (SQLException e) {
                System.out.println("error:" + e);
            }

        }

    }

}
