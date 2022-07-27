<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
session.invalidate(); //로그아웃 링크를 클릭했기때문에, 기존 세션에 있는 데이터를 모두 삭제한다.

response.sendRedirect("loginForm.jsp");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>logout.jsp</title>
</head>
<body>
    
</body>
</html>