<%@page import="java.util.Date"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 	<script>
	    function rollbackList(){
	        location.href="list.jsp";
	    }

    </script>


<%

int id=Integer.parseInt(request.getParameter("id"));
String url = "jdbc:oracle:thin:@db20220510180837_high?TNS_ADMIN=C:/opt/OracleCloudWallet/ATP";
String user="ADMIN";
String pwd="Oracle123456789";
String sql = "SELECT * FROM NOTICE WHERE id=?";


Class.forName("oracle.jdbc.OracleDriver");
Connection conn = DriverManager.getConnection(url,user,pwd);
PreparedStatement stmt = conn.prepareStatement(sql);
stmt.setInt(1, id);
ResultSet rs = stmt.executeQuery();
rs.next();


String title=rs.getString("TITLE");
Date regdate=rs.getDate("REGDATE");
String writerId=rs.getString("WRITER_ID"); 
String hit=rs.getString("HIT");


	rs.close();
	stmt.close();
	conn.close();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
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
    *{
        text-align: center;
    }
    </style>

</head>

<body>
    
    <div class="container">
        <table class="table">
                              
            <tr>
                <th>제목</th>
                <td class="text-align-left text-indent text-strong text-orange" colspan="3">${n.title}</td>
            </tr>
            <tr>
                <th>작성일</th>
                <td class="text-align-left text-indent" colspan="3">${n.regdate}</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${n.writerId}</td>
            </tr>
            <tr>
                <th>조회수</th>
                <td>${n.hit}</td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">${n.files}</td>
            </tr>
            <tr class="content">
                <td colspan="2" >
                <div><br></div>
                <div>${n.content}</div>
                <div>테스트중인 내용입니다.</div>
                
                <div><br></div>
                
                
            </tr>
            <tr>
		       <td>
		           <button onclick="rollbackList()">글목록</button>
		       </td>
        
      </tr>                 
                          
      </div>



    
</body>
</html>
