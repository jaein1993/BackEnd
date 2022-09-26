<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>B</title>
</head>
<body>
    <h1>/redirect/B.jsp</h1>
    <hr>

    <%
        String color= request.getParameter("color");
        String priority= request.getParameter("priority");

    %>

    <!-- <h2> 1.color: <%= color %></h2>
    <h2> 2. priority :<%= priority %></h2>  -->

    <!-- =================================================== -->

    <%

        request.setAttribute("color","blue");
        request.setAttribute("priority","1");


        response.sendRedirect("/redirect/C.jsp");
    
    %>
    

   
</body>
</html>