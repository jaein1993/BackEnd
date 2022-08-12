<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <script>
    function getList(){
        location.href="/write.jsp";
    }

    </script>
<%
String url = "jdbc:oracle:thin:@클라우드 전자지갑";
String user="ADMIN";
String pwd="오라클비밀번호";
String sql = "SELECT * FROM NOTICE";


Class.forName("oracle.jdbc.OracleDriver");
Connection conn = DriverManager.getConnection(url,user,pwd);
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);

%>


    <!DOCTYPE html>
    <html>
    <head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    table {
      border-collapse: collapse;
      border-spacing: 0;
      width: 100%;
      border: 1px solid #ddd;
    }
    
    th, td {
      text-align: left;
      padding: 16px;
    }
    
    tr:nth-child(even) {
      background-color: #f2f2f2;
    }
    </style>
    </head>
    <body>
    
    <h2>게시판 목록</h2>
    
    
    <table>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
      
      <% while(rs.next()){ %>
      <tr>
        <td><%= rs.getInt("ID") %></td> 
        <td><a href="/content.jsp?id=<%= rs.getInt("ID") %>"><%=rs.getString("TITLE") %></td>
        <td><%= rs.getString("WRITER_ID") %></td>
        <td><%= rs.getDate("REGDATE") %></td>
        <td><%= rs.getInt("HIT") %></td> 
      </tr>
      <%} %>
      <tr>
        <td>
            <button onclick="getList()">글등록</button>
        </td>
        
      </tr>
     
    </table>
    
    </body>
    </html>
    