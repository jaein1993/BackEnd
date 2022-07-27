<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>loginForm.jsp</title>
</head>

<body>
    <h1>[로그인]</h1>
    <hr>

    <form action="authentication.jsp" method="post">

        <fieldset>
            <legend></legend>
            <ul>
                <li>
                    <label for="id">아이디</label>
                    <input type="text" name="id">
                </li>

                <li>
                    <label for="pw">비밀번호</label>
                    <input type="password" name="pw">
                </li>
                <li>
                    <input type="submit" value="로그인">
                </li>
            </ul>
        </fieldset>

    </form>

    

    
</body>
</html>