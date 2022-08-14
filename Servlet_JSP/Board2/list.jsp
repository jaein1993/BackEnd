<%@page import="java.util.List"%>
<%@page import="org.zerock.myapp.entity.Notice"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.PreparedStatement"%>
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
      
      <!-- Attribute에 저장된 list를 불러온단
      list에 더 이상 값이 없을떄까지 반복문을 돌면서 
       Notice 객체 하나하나를 이 페이지에서만 쓸 수 있는 Attribute로 따로 저장한다
       이 Attribute의 이름은 n이며, 이제 n이라는 Attrubute로 DB 내용에 접근 가능하다 -->     
      <%
      List<Notice>list=(List<Notice>)request.getAttribute("list");
      for(Notice n:list){
    	  pageContext.setAttribute("n", n);
	  %>
      	
      	<!-- n으로 Notice객체에 접근해서 테이블 한 줄 만들기 (얘네는 아직 for문 안에 있는거임) -->
		<tr>
			<td>${n.id}</td> 
			<!-- title의 경우 클릭하면 a태그를 통해 det.jsp로 넘어가는데 이때 id값도 함께 넘겨준다 -->
			<td class="title indent text-align-left"><a href="det?id=${n.id}">${n.title}</a></td>
			<td>${n.writerId}</td>
			<td>${n.regdate}</td>
			<td>${n.hit}</td> 
		</tr>
  
  	<%} // for-end %>
  	
      <tr>
        <td>
            <button onclick="getList()">글작성</button>
        </td>
        
      </tr>
     
    </table>
    
    </body>
    </html>
   	