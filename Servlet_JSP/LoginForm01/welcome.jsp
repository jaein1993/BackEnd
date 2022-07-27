<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	 //인증된 세션이 없는경우 (비로그인,비회원), 해당페이지를 볼 수 없게 한다.
	 if(session.getAttribute("id")==null){ //인증된아이디가 null값일경우
		 response.sendRedirect("loginForm.jsp");
	 }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>welcome.jsp</title>
</head>
<body>
    <h1><%=session.getAttribute("id")%>님 <br><small>환영합니다.</small></h1> 
    <a href="logout.jsp">로그아웃</a>
    
</body>
</html>

