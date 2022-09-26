<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>A</title>
</head>
<body>
    <h1>/redirect/A.jsp</h1>
    <hr>

    <!-- 웹 브라우저에게 307 리다이렉트 응답을 전송
    307 리다이렉트 응답:: 요청 잘못보내셨네요. 제가 알려드리는쪽으로 요청 다시보내세요~ 방향바꾸세요 A아니고 B.jsp로 보내셔요  -->

        <!-- 빨간색 용지에 최우선순위값 1을 기재해서 B.jsp에게 보여주자
        (1)전송파라미터로 보내자 -->
        <!-- (2)공유영역인 Request Scope에 올려놔서 보내자!(공유시킴) 이건 B에서 할꺼야  -->



	
    <% response.sendRedirect("/redirect/B.jsp?priority=1&color=red"); %>

    
</body>
</html> 