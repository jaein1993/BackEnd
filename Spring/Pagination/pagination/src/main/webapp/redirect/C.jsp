<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

    <%
        String color= (String)request.getParameter("color");
        String priority= (String)request.getParameter("priority");

    %>

        <h2> 1.color: <%= color %></h2>
        <h2> 2. priority :<%= priority %></h2> 
    <h1>/redirect/C.jsp</h1>
    <hr>
</body>
</html>