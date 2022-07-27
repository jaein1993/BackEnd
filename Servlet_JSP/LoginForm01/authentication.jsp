<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%	
	//인증 가능 사용자 및 비밀번호 목록
	
	request.setCharacterEncoding("utf8");

	String [] users ={"sky","sea","tree"};
	String [] passwords ={"abc1234","abc1234","abc1234"};
	
	
	//loginForm파일로부터 전달된 데이터를 변수에 저장
	String id= request.getParameter("id");
	String pw= request.getParameter("pw");
	
	//사용자 인증
	String sendUrl = "loginForm.jsp";
	
	for(int i=0; i<users.length; i++){
		if(users[i].equals(id)&& passwords[i].equals(pw)){ //아이디,비밀번호 일치하면
			sendUrl = "welcome.jsp";//해당 페이지로 이동
		}
	}
	session.setAttribute("id", id); //세션저장소로 id를 넘긴다
	session.setAttribute("pw", pw);	//세션저장소로 password를 넘긴다.
	
	response.sendRedirect(sendUrl);
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <ul>
        <li> <h1><%= session.getAttribute("id") %>님 <small>반갑습니다.</small></h1></li>
        
    </ul>
</body>
</html>